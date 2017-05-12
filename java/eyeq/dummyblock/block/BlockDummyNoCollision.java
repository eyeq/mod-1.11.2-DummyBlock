package eyeq.dummyblock.block;

import eyeq.dummyblock.DummyBlock;
import eyeq.util.block.UBlockDummy;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BlockDummyNoCollision extends UBlockDummy {
    public BlockDummyNoCollision() {
        super(Material.GLASS);
        this.setUnlocalizedName("dummyBlock");
        this.setCreativeTab(DummyBlock.TAB_DUMMY);
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean p_185477_7_) {
    }
}
