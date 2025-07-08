package org.verselstudios.verselsmultiverse.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class BlackJackTable extends HorizontalDirectionalBlock {

    private static final VoxelShape BASE_SHAPE = Shapes.block();
    private static final VoxelShape NORTH_SOUTH_SHAPE = Shapes.box(2/16f, 14/16f, -14/16f, 14/16f, 16/16f, 30/16f);
    private static final VoxelShape EAST_WEST_SHAPE = Shapes.box(-14/16f, 14/16f, 2/16f, 30/16f, 16/16f, 14/16f);

    public static final MapCodec<BlackJackTable> CODEC = simpleCodec(BlackJackTable::new);

    public BlackJackTable(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }


    @Override
    protected @NotNull MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = super.getStateForPlacement(context);
        return blockstate.setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(FACING).equals(Direction.NORTH) || state.getValue(FACING).equals(Direction.SOUTH)) {
            return Shapes.or(BASE_SHAPE, NORTH_SOUTH_SHAPE);
        } else {
            return Shapes.or(BASE_SHAPE, EAST_WEST_SHAPE);
        }
    }

    @Override
    protected @NotNull VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(FACING).equals(Direction.NORTH) || state.getValue(FACING).equals(Direction.SOUTH)) {
            return Shapes.or(BASE_SHAPE, NORTH_SOUTH_SHAPE);
        } else {
            return Shapes.or(BASE_SHAPE, EAST_WEST_SHAPE);
        }
    }
}
