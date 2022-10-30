package com.confusedparrotfish.predicate.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

public class blocknetwork {
    public HashMap<Block, connectionmanager> rules = new HashMap<Block, connectionmanager>();

    public ArrayList<BlockPos> visited = new ArrayList<>();

    public void calculate(ServerWorld world, BlockPos pos) {
        Vector<BlockPos> queue = new Vector<BlockPos>();

        // Append the position of starting
        // pixel of the component
        queue.add(pos);

        visited.add(pos);

        int depth = 0;
        while (queue.size() > 0 && depth < 600) {
            depth++;
            
            BlockPos current = queue.get(queue.size() - 1);

            if (rules.containsKey(world.getBlockState(queue.get(queue.size() - 1)).getBlock())) {
                
                connectionrules crule = rules.get(world.getBlockState(current).getBlock()).generateconection(world, pos);

                queue.remove(queue.size() - 1);

                // d-sides

                if (crule.down && !visited.contains(current.down()) && rules.containsKey(world.getBlockState(current.down()).getBlock())) {
                    visited.add(current.down());
                    queue.add(current.down());
                }

                if (crule.up && !visited.contains(current.up()) && rules.containsKey(world.getBlockState(current.up()).getBlock())) {
                    visited.add(current.up());
                    queue.add(current.up());
                }

                if (crule.north && !visited.contains(current.north()) && rules.containsKey(world.getBlockState(current.north()).getBlock())) {
                    visited.add(current.north());
                    queue.add(current.north());
                }

                if (crule.south && !visited.contains(current.south()) && rules.containsKey(world.getBlockState(current.south()).getBlock())) {
                    visited.add(current.south());
                    queue.add(current.south());
                }

                if (crule.east && !visited.contains(current.east()) && rules.containsKey(world.getBlockState(current.east()).getBlock())) {
                    visited.add(current.east());
                    queue.add(current.east());
                }

                if (crule.west && !visited.contains(current.west()) && rules.containsKey(world.getBlockState(current.west()).getBlock())) {
                    visited.add(current.west());
                    queue.add(current.west());
                }
            }
        }
    }

    public void update(ServerWorld world, BlockPos pos, connectionrules cnnc) {
        //
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
}
