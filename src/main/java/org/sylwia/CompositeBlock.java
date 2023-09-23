package org.sylwia;

import java.util.List;
//made public for tests

public interface CompositeBlock extends Block {
    List<Block> getBlocks();
}
