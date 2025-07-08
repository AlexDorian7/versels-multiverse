package org.verselstudios.verselsmultiverse.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.verselstudios.verselsmultiverse.blocks.entity.BlackJackTableBlockEntity;
import org.verselstudios.verselsmultiverse.menu.BlackJackTableMenu;

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

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity == null || !(blockEntity instanceof BlackJackTableBlockEntity blackJackTableBlockEntity)) return null;
        return new SimpleMenuProvider(blackJackTableBlockEntity::createMenu, Component.translatable("blockEntity.verselsmultiverse.black_jack_table.default_title"));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(state.getMenuProvider(level, pos));
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
