package com.confusedparrotfish.predicate.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class blocknetwork {
    public HashMap<BlockPos, connectionrules> path = new HashMap<>();

    public HashMap<Block, connectionmanager> rules = new HashMap<Block, connectionmanager>();

    public ArrayList<nxt<String>> results = new ArrayList<nxt<String>>();

    private ArrayList<BlockPos> depthmap = new ArrayList<BlockPos>();

    public void calculate(ServerWorld world, BlockPos pos, connectionrules init) {
        results = new ArrayList<nxt<String>>();
        path = new HashMap<BlockPos, connectionrules>();

        nxt<String> data = new nxt<String>();

        path.put(pos, rules.get(world.getBlockState(pos).getBlock()).generateconection(world, pos));

        if (init.south)
            results = mixlist(results, step(world, pos.south(), pos, data, 0));
        if (init.down)
            results = mixlist(results, step(world, pos.down(), pos, data, 0));
        if (init.north)
            results = mixlist(results, step(world, pos.north(), pos, data, 0));
        if (init.west)
            results = mixlist(results, step(world, pos.west(), pos, data, 0));
        if (init.east)
            results = mixlist(results, step(world, pos.east(), pos, data, 0));
        if (init.up)
            results = mixlist(results, step(world, pos.up(), pos, data, 0));
    }

    private ArrayList<nxt<String>> step(ServerWorld world, BlockPos current, BlockPos past, nxt<String> pasthrough,
            int depth) {
        Block crn = world.getBlockState(current).getBlock();
        ArrayList<nxt<String>> backpass = new ArrayList<>();
        // confirm
        if (depth+1 < 600) {
            if (rules.containsKey(crn) && !path.containsKey(current)) {
                // generate connection
                connectionrules crule = rules.get(crn).generateconection(world, current);

                pasthrough = rules.get(crn).passthrough(pasthrough, current, past);

                if (crule.pass)
                    backpass.add(pasthrough);

                // parse connection//recurse
                if (crule.south && (past != current.south()) && !path.containsKey(current.south()))
                    backpass = mixlist(backpass, step(world, current.south(), current, pasthrough, depth + 1));
                if (crule.down && (past != current.down()) && !path.containsKey(current.down()))
                    backpass = mixlist(backpass, step(world, current.down(), current, pasthrough, depth + 1));
                if (crule.north && (past != current.north()) && !path.containsKey(current.north()))
                    backpass = mixlist(backpass, step(world, current.north(), current, pasthrough, depth + 1));
                if (crule.west && (past != current.west()) && !path.containsKey(current.west()))
                    backpass = mixlist(backpass, step(world, current.west(), current, pasthrough, depth + 1));
                if (crule.east && (past != current.east()) && !path.containsKey(current.east()))
                    backpass = mixlist(backpass, step(world, current.east(), current, pasthrough, depth + 1));
                if (crule.up && (past != current.up()) && !path.containsKey(current.up()))
                    backpass = mixlist(backpass, step(world, current.up(), current, pasthrough, depth + 1));
                // return
                path.put(current, crule);
            }
        } else {
            // TODO: chat error

            depthmap.add(current);
        }

        return backpass;
    }

    public void update(ServerWorld world, BlockPos pos, connectionrules cnnc) {
        boolean regen = false;
        Iterator<Entry<BlockPos, connectionrules>> entries = path.entrySet().iterator();

        while (entries.hasNext()) {
            Entry<BlockPos, connectionrules> entry = entries.next();

            if (!regen && rules.containsKey(world.getBlockState(entry.getKey()).getBlock()))
                if (((connectionmanager) rules.get(world.getBlockState(entry.getKey()).getBlock()))
                        .generateconection(world, pos) != entry.getValue()) {
                    regen = true;
                } else
                    regen = true;
        }

        if (regen || path.size() == 0) {
            calculate(world, pos, cnnc);
        }
    }

    public static class connectionrules {
        public boolean up = false;
        public boolean down = false;
        public boolean west = false;
        public boolean east = false;
        public boolean north = false;
        public boolean south = false;

        public boolean pass = false;

        public String id = "";

        public connectionrules(boolean u, boolean d, boolean w, boolean e, boolean n, boolean s) {
            up = u;
            down = d;
            west = w;
            east = e;
            north = n;
            south = s;
        }

        public connectionrules() {
        }
    }

    public static class connectionmanager {
        public connectionrules generateconection(ServerWorld world, BlockPos pos) {
            return null;
        }

        public nxt<String> passthrough(nxt<String> pasthrough, BlockPos current, BlockPos past) {
            return null;
        }
    }

    public static ArrayList<nxt<String>> mixlist(ArrayList<nxt<String>> one, ArrayList<nxt<String>> two) {
        for (nxt<String> object : two) {
            one.add(object);
        }
        return one;
    }

    static HashMap<BlockPos, connectionrules> sortpath(HashMap<BlockPos, connectionrules> hm) {
        HashMap<BlockPos, connectionrules> temp = hm.entrySet()
                .stream()
                .sorted((i1, i2) -> {
                    return ((Integer) (int) BlockPos.pack(i1.getKey().getX(), i1.getKey().getY(), i1.getKey().getZ()))
                            .compareTo(
                                    ((Integer) (int) BlockPos.pack(i2.getKey().getX(), i2.getKey().getY(),
                                            i2.getKey().getZ())));
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        return temp;
    }

    @Override
    public String toString() {
        String str = "";

        str += "\nblock network printout\n_______________________________________________\n\n";
        str += "path length: " + path.size() + "\n";

        // depthmap
        str += "\ndepth overexess:\n";

        if (depthmap.size() <= 100) {
            for (BlockPos bp : depthmap) {
                str += "  x:" + bp.getX()+", y:"+bp.getY()+", z:"+bp.getZ() + "\n";
            }
        } 
        else if (depthmap.size() == 0) {
            str += "no depth breaches to display!\n";
        } 
        else {
            str += "  too many depth breaches to display.\n     |-as: " + depthmap.size();
        }

        return str;
    }
}
