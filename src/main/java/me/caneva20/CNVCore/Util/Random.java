package me.caneva20.CNVCore.Util;

import org.bukkit.util.Vector;

@SuppressWarnings("unused")
public class Random {
    private static long lastSeed;
    private static java.util.Random random;

    public static java.util.Random getRandom () {
        if (random == null) {
            random = new java.util.Random();
        }

        long seed = lastSeed += System.currentTimeMillis() * System.currentTimeMillis();
        random.setSeed(seed);

        return random;
    }

    public static int randomInt() {
        return getRandom().nextInt();
    }

    public static double randomDouble() {
        return getRandom().nextDouble();
    }

    public static long randomLong() {
        return getRandom().nextLong();
    }

    public static int range (int min, int max) {
        return (int) (getRandom().nextFloat() * (max - min) + min);
    }

    public static double range (double min, double max) {
        return getRandom().nextFloat() * (max - min) + min;
    }

    public static long range (long min, long max) {
        return (long) (getRandom().nextFloat() * (max - min) + min);
    }

    public static Vector randomVector(Vector minimum, Vector maximum) {
        int x = Random.range(minimum.getBlockX() - 1, maximum.getBlockX() + 1);
        int y = Random.range(minimum.getBlockY() - 1, maximum.getBlockY() + 1);
        int z = Random.range(minimum.getBlockZ() - 1, maximum.getBlockZ() + 1);

        return new Vector(x, y, z);
    }
}











