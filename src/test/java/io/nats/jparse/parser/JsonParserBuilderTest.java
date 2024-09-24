package io.nats.jparse.parser;

import io.nats.jparse.Json;
import io.nats.jparse.node.ObjectNode;
import io.nats.jparse.parser.JsonParser;
import io.nats.jparse.parser.JsonParserBuilder;
import io.nats.jparse.parser.indexoverlay.JsonFastParser;
import io.nats.jparse.parser.indexoverlay.JsonStrictParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonParserBuilderTest {

    private JsonParserBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new JsonParserBuilder();
    }

    @Test
    void testDefaultBuilder() {
        JsonParser parser = builder.build();
        assertFalse(builder.strict());
        assertFalse(builder.objectsKeysCanBeEncoded());
        assertTrue(parser instanceof JsonFastParser);
    }

    @Test
    void testStrictParser() {
        builder.setStrict(true);
        JsonParser parser = builder.build();
        assertTrue(builder.strict());
        assertTrue(parser instanceof JsonStrictParser);
    }

    @Test
    void testObjectKeysEncoded() {
        builder.setObjectsKeysCanBeEncoded(true);
        JsonParser parser = builder.build();
        assertTrue(builder.objectsKeysCanBeEncoded());
        assertTrue(parser instanceof JsonFastParser);
    }

    @Test
    void testBuilderMethodChaining() {
        builder.setStrict(true).setObjectsKeysCanBeEncoded(true);
        assertTrue(builder.strict());
        assertTrue(builder.objectsKeysCanBeEncoded());
        JsonParser parser = builder.build();
        assertTrue(parser instanceof JsonStrictParser);
    }

    @Test
    void testStaticBuilderMethod() {
        JsonParserBuilder newBuilder = JsonParserBuilder.builder();
        assertNotNull(newBuilder);
        assertTrue(newBuilder instanceof JsonParserBuilder);
    }

    @Test
    void testMultipleBuilds() {
        JsonParser parser1 = builder.build();
        assertTrue(parser1 instanceof JsonFastParser);

        builder.setStrict(true);
        JsonParser parser2 = builder.build();
        assertTrue(parser2 instanceof JsonStrictParser);

        builder.setStrict(false);
        JsonParser parser3 = builder.build();
        assertTrue(parser3 instanceof JsonFastParser);
    }

    @Test
    public void testObjectsKeysCanBeEncodedPropagation() {
        JsonParserBuilder builder = new JsonParserBuilder().setObjectsKeysCanBeEncoded(true);
        JsonFastParser parser = (JsonFastParser) builder.build();

        String json = Json.niceJson("{'hi': 'how are you?'}");

        ObjectNode objectNode = parser.parse(json).getObjectNode();

        String value = objectNode.getString("hi");
        assertEquals("how are you?", value);
    }
}
