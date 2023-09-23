package org.sylwia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    //only for tests
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    private List<Block> blocks;

    @Override //added code
    public Optional<Block> findBlockByColor(String color) {
        Optional<Block> coloredBlock = blocks.stream()
                .filter(currentBlock -> !(currentBlock instanceof CompositeBlock))
                .filter(currentBlock -> currentBlock.getColor().equals(color))
                .findFirst();

        if (coloredBlock.isPresent())
            return coloredBlock;

        return blocks.stream()
                .filter(currentBlock -> currentBlock instanceof CompositeBlock)
                .map(c -> (CompositeBlock) c)
                .map(CompositeBlock::getBlocks)
                .flatMap(Collection::stream)
                .filter(currentBlock -> currentBlock.getColor().equals(color))
                .findFirst();

    }

    @Override //added code
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> allBlocks = new ArrayList<>();
        allBlocks.addAll(
                blocks.stream()
                        .filter(currentBlock -> !(currentBlock instanceof CompositeBlock))
                        .filter(currentBlock -> currentBlock.getMaterial().equals(material))
                        .toList()
        );
        allBlocks.addAll(
                blocks.stream()
                        .filter(currentBlock -> currentBlock instanceof CompositeBlock)
                        .map(c -> (CompositeBlock) c)
                        .map(CompositeBlock::getBlocks)
                        .flatMap(Collection::stream)
                        .filter(currentBlock -> currentBlock.getMaterial().equals(material))
                        .toList()
        );
        return allBlocks;

    }

    @Override //added code
    public int count() {
        return blocks.stream()
                .mapToInt(currentBlock -> {
                    if (currentBlock instanceof CompositeBlock) {
                        return ((CompositeBlock) currentBlock).getBlocks().size();
                    } else {
                        return 1;
                    }
                })
                .sum();

    }
}
