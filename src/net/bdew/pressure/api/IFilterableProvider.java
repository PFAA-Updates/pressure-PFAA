/*
 * Copyright (c) bdew, 2013 - 2014
 * https://github.com/bdew/pressure
 *
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license located in
 * http://bdew.net/minecraft-mod-public-license/
 */

package net.bdew.pressure.api;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public interface IFilterableProvider {
    /**
     * Provide IFilterable for something in the world
     *
     * @return IFilterable instance or null if not applicable
     */
    IFilterable getFilterableForWorldCoordinates(World world, BlockPos pos, EnumFacing side);
}
