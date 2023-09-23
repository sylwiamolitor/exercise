import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sylwia.Block;
import org.sylwia.CompositeBlock;
import org.sylwia.Wall;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WallTests {

    private static final List<Block> BLOCKS = new ArrayList<>();
    private static final List<Block> BLOCKS2 = new ArrayList<>();


    private static final Wall WALL = new Wall();

    @BeforeAll //mocked blocks' logic for tests
    static void createList() {
        BLOCKS.add(new Block() {
            @Override
            public String getColor() {
                return "red";
            }

            @Override
            public String getMaterial() {
                return "wood";
            }
        });
        BLOCKS.add(new Block() {
            @Override
            public String getColor() {
                return "yellow";
            }

            @Override
            public String getMaterial() {
                return "glass";
            }
        });
        BLOCKS2.add(new Block() {
            @Override
            public String getColor() {
                return "red";
            }

            @Override
            public String getMaterial() {
                return "wood";
            }
        });
        BLOCKS2.add(new Block() {
            @Override
            public String getColor() {
                return "red";
            }

            @Override
            public String getMaterial() {
                return "wood";
            }
        });
        BLOCKS.add(new CompositeBlock() {

            @Override
            public List<Block> getBlocks() {
                return BLOCKS2;
            }

            @Override
            public String getColor() {
                return "red";
            }

            @Override
            public String getMaterial() {
                return "wood";
            }
        });
        WALL.setBlocks(BLOCKS);

    }

    @ParameterizedTest
    @ValueSource(strings = {"red"})
    public void findBlockByColorTest(String color) {
        Optional<Block> currentBlock = WALL.findBlockByColor(color);

        Assertions.assertTrue(currentBlock.isPresent());
        Assertions.assertEquals(color, currentBlock.get().getColor());

    }

    @ParameterizedTest
    @ValueSource(strings = {"blue"})
    public void findNoBlockByColorTest(String color) {
        Optional<Block> currentBlock = WALL.findBlockByColor(color);

        Assertions.assertFalse(currentBlock.isPresent());
    }


    @ParameterizedTest
    @ValueSource(strings = {"wood"})
    public void findBlocksByMaterialTest(String material) {
        List<Block> currentBlocks = WALL.findBlocksByMaterial(material);

        Assertions.assertEquals(3, currentBlocks.size());
        Assertions.assertEquals(material, currentBlocks.get(0).getMaterial());
    }

    @ParameterizedTest
    @ValueSource(strings = {"metal"})
    public void findNoBlocksByMaterialTest(String material) {
        List<Block> currentBlocks = WALL.findBlocksByMaterial(material);

        Assertions.assertEquals(0, currentBlocks.size());
    }

    @ParameterizedTest
    @ValueSource(ints = 4)
    public void countTest(int count) {
        int currentCount = WALL.count();

        Assertions.assertEquals(count, currentCount);
    }

}
