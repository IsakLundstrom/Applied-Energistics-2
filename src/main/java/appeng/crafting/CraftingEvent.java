/*
 * This file is part of Applied Energistics 2.
 * Copyright (c) 2021, TeamAppliedEnergistics, All rights reserved.
 *
 * Applied Energistics 2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Applied Energistics 2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Applied Energistics 2.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package appeng.crafting;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import appeng.crafting.pattern.AECraftingPattern;
import appeng.util.Platform;

public class CraftingEvent {

    public static void fireCraftingEvent(Player player,
            ItemStack craftedItem,
            Container container) {
        // TODO FABRIC 117 Expose an event for this in the API
//        MinecraftForge.EVENT_BUS.post(new PlayerEvent.ItemCraftedEvent(player, craftedItem, container));
    }

    public static void fireAutoCraftingEvent(Level level,
            AECraftingPattern pattern,
            Container container) {
        var craftedItem = pattern.getOutput(container, level);
        fireAutoCraftingEvent(level, pattern, craftedItem, container);
    }

    public static void fireAutoCraftingEvent(Level level,
            // NOTE: We want to be able to include the recipe in the event later
            @SuppressWarnings("unused") AECraftingPattern pattern,
            ItemStack craftedItem,
            Container container) {
        var serverLevel = (ServerLevel) level;
        var fakePlayer = Platform.getPlayer(serverLevel);
        // TODO FABRIC 117 Expose an event for this in the API
//        MinecraftForge.EVENT_BUS.post(new PlayerEvent.ItemCraftedEvent(fakePlayer, craftedItem, container));
    }

}
