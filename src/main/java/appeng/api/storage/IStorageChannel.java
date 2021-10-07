/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 AlgorithmX2
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package appeng.api.storage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import appeng.api.storage.data.IAEStack;
import appeng.api.storage.data.IAEStackList;

public interface IStorageChannel<T extends IAEStack> {

    /**
     * @return The unique ID of this storage channel.
     */
    @Nonnull
    ResourceLocation getId();

    /**
     * Can be used as factor for transferring stacks of a channel.
     * <p>
     * E.g. used by IO Ports to transfer 1000 mB, not 1 mB to match the item channel transferring a full bucket per
     * operation.
     */
    default int transferFactor() {
        return 1;
    }

    /**
     * The number of units (eg item count, or millibuckets) that can be stored per byte in a storage cell. Standard
     * value for items is 8, and for fluids it's 8000
     *
     * @return number of units
     */
    default int getUnitsPerByte() {
        return 8;
    }

    /**
     * Create a new {@link IAEStackList} of the specific type.
     */
    @Nonnull
    IAEStackList<T> createList();

    /**
     * Create a new {@link IAEStack} from an item stack.
     * <p>
     * While this is a clearly defined operation for {@link ItemStorageChannel}, other storage channels might not
     * implement this method, and always return null. They may implement it for items that have a mapping into their
     * storage channel domain (i.e. the display-only item returned by {@link IAEStack#asItemStackRepresentation()} might
     * be able to be turned back into an AE stack using this method).
     *
     * @param input The item stack to turn into an {@link IAEStack}
     * @return The converted stack or null
     */
    @Nullable
    T createStack(@Nonnull ItemStack input);

    @Nullable
    T readFromPacket(@Nonnull FriendlyByteBuf input);

    /**
     * create from nbt data
     */
    @Nullable
    T createFromNBT(@Nonnull CompoundTag nbt);

    T copy(T stack);
}