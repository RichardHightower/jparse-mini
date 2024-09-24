# .

## ./

## io/

### nats/

#### jparse/

##### Json.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse;

import io.nats.jparse.node.ArrayNode;
import io.nats.jparse.node.Node;
import io.nats.jparse.node.ObjectNode;
import io.nats.jparse.node.RootNode;
import io.nats.jparse.parser.JsonParser;
import io.nats.jparse.parser.JsonParserBuilder;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.token.Token;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The `Json` class provides static utility methods for working with JSON data. It includes methods for parsing JSON
 * data into various data structures, converting data structures to JSON, and scanning JSON data for tokens.
 */
public class Json {

    private Json() {
    }

    /**
     * Returns a new `JsonParserBuilder` instance, which can be used to configure and create `JsonParser` instances.
     *
     * @return A new `JsonParserBuilder` instance
     */
    public static JsonParserBuilder builder() {
        return new JsonParserBuilder();
    }

    /**
     * Returns a string containing a "nicely formatted" version of the input JSON string.
     *
     * @param json The JSON string to format
     * @return A string containing a "nicely formatted" version of the input JSON string
     */
    public static String niceJson(String json) {
        char[] chars = json.toCharArray();
        StringBuilder sb = new StringBuilder(chars.length);
        for (char c : chars) {
            if (c == '\'') {
                sb.append('"');
            } else if (c == '`') {
                sb.append('\\');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Parses the input JSON string into an `ArrayNode`.
     *
     * @param json The JSON string to parse
     * @return An `ArrayNode` representing the parsed JSON data
     */
    public static ArrayNode toArrayNode(final String json) {
        return new JsonParserBuilder().build().parse(json).getArrayNode();
    }

    /**
     * Parses the input JSON string into an `ObjectNode`.
     *
     * @param json The JSON string to parse
     * @return An `ObjectNode` representing the parsed JSON data
     */
    public static ObjectNode toObjectNode(final String json) {
        return new JsonParserBuilder().build().parse(json).getObjectNode();
    }

    /**
     * Parses the input JSON string into a `RootNode`.
     *
     * @param json The JSON string to parse
     * @return A `RootNode` representing the parsed JSON data
     */
    public static RootNode toRootNode(final String json) {
        return new JsonParserBuilder().build().parse(json);
    }

    /**
     * Parses the input JSON string into a `List` of `Object`s.
     *
     * @param json The JSON string to parse
     * @return A `List` of `Object`s representing the parsed JSON data
     */
    public static List<Object> toList(final String json) {
        return (List<Object>) (Object) toArrayNode(json);
    }

    /**
     * Parses the input JSON string into a `Map` of `String`s to `Object`s.
     *
     * @param json The JSON string to parse
     * @return A `Map` of `String`s to `Object`s representing the parsed JSON data
     */
    public static Map<String, Object> toMap(final String json) {
        return (Map<String, Object>) (Object) toObjectNode(json);
    }

    /**
     * Scans the input JSON string and returns a `List` of `Token`s.
     *
     * @param json The JSON string to scan
     * @return A `List` of `Token`s representing the scanned JSON data
     */
    public static List<Token> toTokens(final String json) {
        return new JsonParserBuilder().build().scan(json);
    }

    /**
     * Parses the input `CharSource` object into an `ArrayNode`.
     *
     * @param json The `CharSource` object to parse
     * @return An `ArrayNode` representing the parsed JSON data
     */
    public static ArrayNode toArrayNode(final CharSource json) {
        return new JsonParserBuilder().build().parse(json).getArrayNode();
    }

    /**
     * Parses the input `CharSource` object into an `ObjectNode`.
     *
     * @param json The `CharSource` object to parse
     * @return An `ObjectNode` representing the parsed JSON data
     */
    public static ObjectNode toObjectNode(final CharSource json) {
        return new JsonParserBuilder().build().parse(json).getObjectNode();
    }

    /**
     * Parses the input JSON string into a `RootNode`.
     *
     * @param json The JSON source to parse
     * @return A `RootNode` representing the parsed JSON data
     */
    public static RootNode toRootNode(final CharSource json) {
        return new JsonParserBuilder().build().parse(json);
    }

    /**
     * Parses the input JSON string into a `List` of `Object`s.
     *
     * @param json The JSON char source to parse
     * @return A `List` of `Object`s representing the parsed JSON data
     */
    public static List<Object> toList(final CharSource json) {
        return (List<Object>) (Object) new JsonParserBuilder().build().parse(json).getArrayNode();
    }

    /**
     * Parses the input JSON string into a `Map` of `String`s to `Object`s.
     *
     * @param json The JSON char source to parse
     * @return A `Map` of `String`s to `Object`s representing the parsed JSON data
     */
    public static Map<String, Object> toMap(final CharSource json) {
        return (Map<String, Object>) (Object) new JsonParserBuilder().build().parse(json).getObjectNode();
    }

    /**
     * Scans the input JSON string and returns a `List` of `Token`s.
     *
     * @param json The JSON char source to scan
     * @return A `List` of `Token`s representing the scanned JSON data
     */
    public static List<Token> toTokens(final CharSource json) {
        return new JsonParserBuilder().build().scan(json);
    }


    /**
     * Serialize the given {@link Object} to a String, the object must be a `Node`.
     *
     * @param object the object to serialize
     * @return the String representation of the object, or empty string if the object is null
     * @see Node
     */
    public static String serializeToString(Object object) {
        return ((Node) object).originalString();
    }

    /**
     * Serialize the given {@link Object} to a {@link CharSequence}, the object must be a `Node.
     *
     * @param object the object to serialize
     * @return the CharSequence representation of the object, or empty CharSequence if the object is null
     */
    public static CharSequence serialize(Object object) {
        return ((Node) object).originalCharSequence();
    }
}

```

##### Path.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse;

import io.nats.jparse.node.ArrayNode;
import io.nats.jparse.node.Node;
import io.nats.jparse.node.ObjectNode;
import io.nats.jparse.path.PathElement;
import io.nats.jparse.path.PathNode;
import io.nats.jparse.path.PathParser;
import io.nats.jparse.source.support.PathException;

import java.util.Iterator;


/**
 * The `Path` class provides utility methods for working with JSON paths. It includes methods for parsing
 * JSON paths, looking up nodes at specified paths, and converting paths to `PathNode` objects.
 *
 * @see Node
 * @see Json
 * @see PathNode
 */
public class Path {

    private Path(){}

    /**
     * Finds the node at the specified path in the input JSON string.
     *
     * @param path The path to search for
     * @param json The input JSON string
     * @return The node at the specified path
     */
    public static Node atPath(final String path, final String json) {
        return atPath(path, Json.toRootNode(json));
    }

    /**
     * Finds the node at the specified path in the input `Node`.
     *
     * @param path     The path to search for
     * @param rootNode The input `Node`
     * @return The node at the specified path
     */
    public static Node atPath(final String path, final Node rootNode) {
        return atPath(toPath(path), rootNode);
    }

    /**
     * Finds the node at the specified path in the input `Node`.
     *
     * @param path     The `PathNode` representing the path to search for
     * @param rootNode The input `Node`
     * @return The node at the specified path
     * @see Node
     */
    public static Node atPath(final PathNode path, final Node rootNode) {
        Iterator<PathElement> iterator = path.iterator();
        Node node = rootNode;
        PathElement pathElement = null;
        try {
            while (iterator.hasNext()) {

                pathElement = iterator.next();

                switch (node.type()) {
                    case OBJECT:
                        final ObjectNode objectNode = (ObjectNode) node;
                        final CharSequence key = pathElement.asKey().toCharSequence();
                        node = objectNode.getNode(key);
                        break;
                    case ARRAY:
                        final ArrayNode arrayNode = (ArrayNode) node;
                        node = arrayNode.getNodeAt(pathElement.asIndex().intValue());
                        break;
                    default:
                        if (node.isCollection()) {
                            node = node.asCollection().getNode(pathElement.asKey().toCharSequence());
                        } else {
                            throw new PathException("Looking up Path", "Path not found at " + path + " path element key " + pathElement.asKey().toString(),
                                    node.charSource(), node.rootElementToken().startIndex);

                        }
                }

            }
        } catch (Exception ex) {
            throw new IllegalStateException("Path not found at " + path + " path element index " + pathElement.value());
        }
        return node;

    }

    /**
     * Converts the input path string to a `PathNode` object.
     *
     * @param path The input path string
     * @return The `PathNode` representing the input path string
     */
    public static PathNode toPath(final String path) {
        final PathParser pathParser = new PathParser();
        return pathParser.parse(path).getPathNode();
    }
}

```

##### token/

###### TokenTypes.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.token;

/**
 * The TokenTypes interface defines constants for the different token types used during JSON parsing. It includes
 * constants for object, attribute key, attribute value, array, and array item tokens, as well as for integer, float,
 * string, boolean, and null tokens. The interface also includes a method for getting a human-readable name for a
 * token type based on its integer value.
 * This interface contains the integer codes representing different token types that might be encountered during parsing.
 */
public interface TokenTypes {

    /**
     * Token type representing a JSON object.
     */
    int OBJECT_TOKEN = 0;

    /**
     * Token type representing a key in a JSON object attribute.
     */
    int ATTRIBUTE_KEY_TOKEN = 1;

    /**
     * Token type representing a value in a JSON object attribute.
     */
    int ATTRIBUTE_VALUE_TOKEN = 2;

    /**
     * Token type representing a JSON array.
     */
    int ARRAY_TOKEN = 3;

    /**
     * Token type representing an item in a JSON array.
     */
    int ARRAY_ITEM_TOKEN = 4;

    /**
     * Token type representing an integer value.
     */
    int INT_TOKEN = 5;

    /**
     * Token type representing a floating point value.
     */
    int FLOAT_TOKEN = 6;

    /**
     * Token type representing a string value.
     */
    int STRING_TOKEN = 7;

    /**
     * Token type representing a boolean value.
     */
    int BOOLEAN_TOKEN = 8;

    /**
     * Token type representing a null value.
     */
    int NULL_TOKEN = 9;

    /**
     * Token type representing a key in a JSON path.
     */
    int PATH_KEY_TOKEN = 10;

    /**
     * Token type representing an index in a JSON path.
     */
    int PATH_INDEX_TOKEN = 11;

    /**
     * Returns a human-readable name for a token type based on its integer value.
     *
     * @param tokenType The integer token type to get the name for
     * @return A human-readable name for the token type
     */
    static String getTypeName(final int tokenType) {
        switch (tokenType) {
            case OBJECT_TOKEN:
                return "Object";
            case ATTRIBUTE_KEY_TOKEN:
                return "Key";
            case ATTRIBUTE_VALUE_TOKEN:
                return "Attribute Value";
            case ARRAY_TOKEN:
                return "Array";
            case ARRAY_ITEM_TOKEN:
                return "Array Item";
            case INT_TOKEN:
                return "Integer";
            case FLOAT_TOKEN:
                return "Float";
            case STRING_TOKEN:
                return "String";
            case BOOLEAN_TOKEN:
                return "Boolean";
            case NULL_TOKEN:
                return "Null";
            default:
                return String.valueOf(tokenType);
        }
    }
}

```

###### Token.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.token;

import io.nats.jparse.source.CharSource;

import java.util.Objects;

/**
 * The Token class represents a token that has been parsed from a JSON string. It includes the start and end indices
 * of the token within the source, as well as its type (@see TokenTypes). The class provides methods for getting the
 * string representation of the token, as well as its length and a string representation that includes its start
 * and end indices and type. Additionally, the class includes equals and hashCode methods for comparing tokens.
 *
 * @see TokenTypes
 */
public class Token {

    /**
     * The start index of the token within the source.
     */
    public final int startIndex;

    /**
     * The end index of the token within the source.
     */
    public final int endIndex;

    /**
     * The type of the token (@see TokenTypes).
     */
    public final int type;

    /**
     * Creates a new Token object with the specified start and end indices and type.
     *
     * @param startIndex The start index of the token
     * @param endIndex   The end index of the token
     * @param type       The type of the token
     */
    public Token(int startIndex, int endIndex, int type) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.type = type;
    }

    /**
     * Returns the string representation of the token from the specified buffer.
     *
     * @param buffer The buffer to read from
     * @return The string representation of the token
     */
    public String asString(String buffer) {
        return buffer.substring(startIndex, endIndex);
    }

    /**
     * Returns the string representation of the token from the specified `CharSource`.
     *
     * @param source The `CharSource` to read from
     * @return The string representation of the token
     */
    public String asString(CharSource source) {
        return source.getString(startIndex, endIndex);
    }

    /**
     * Returns the length of the token.
     *
     * @return The length of the token
     */
    public int length() {
        return endIndex - startIndex;
    }

    /**
     * Returns a string representation of the token that includes its start and end indices and type.
     *
     * @return A string representation of the token
     */
    @Override
    public String toString() {
        return "Token{" +
                "startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                ", type=" + TokenTypes.getTypeName(type) + " " + type +
                '}';
    }

    /**
     * Compares this token with the specified object for equality.
     *
     * @param o The object to compare to
     * @return `true` if the objects are equal; `false` otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return startIndex == token.startIndex && endIndex == token.endIndex && type == token.type;
    }

    /**
     * Returns a hash code for this token.
     *
     * @return A hash code for this token
     */
    @Override
    public int hashCode() {
        return Objects.hash(startIndex, endIndex, type);
    }
}

```

##### path/

###### PathNode.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.path;

import io.nats.jparse.node.CollectionNode;
import io.nats.jparse.node.Node;
import io.nats.jparse.node.NodeType;
import io.nats.jparse.node.support.NodeUtils;
import io.nats.jparse.node.support.TokenSubList;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.token.Token;

import java.util.*;
import java.util.stream.Collectors;
/**
 * PathNode class represents a node in the parsed path of a structured data source such as JSON or XML.
 * It extends AbstractList and implements the CollectionNode interface.
 */
public class PathNode extends AbstractList<PathElement> implements CollectionNode {

    /**
     * The list of tokens associated with this path node.
     */
    private final TokenSubList tokens;

    /**
     * The source of characters where this path node comes from.
     */
    private final CharSource source;

    /**
     * The root token of this path node.
     */
    private final Token rootToken;

    /**
     * The hash code of this path node. This is computed and cached for performance.
     */
    private int hashCode;

    /**
     * The list of child tokens of this path node.
     */
    private List<List<Token>> childrenTokens;

    /**
     * The array of elements (node structures) represented by this path node.
     */
    private Node[] elements;

    /**
     * A flag indicating whether the hashCode has been set for this path node.
     */
    private boolean hashCodeSet;

    /**
     * Constructor for the PathNode class.
     *
     * @param tokens The list of tokens to associate with this path node.
     * @param source The character source from which this path node originates.
     */
    public PathNode(final TokenSubList tokens, final CharSource source) {
        this.tokens = tokens;
        this.rootToken = tokens.get(0);
        this.source = source;
    }

    /**
     * Retrieves the list of child tokens associated with this path node.
     * If the list has not been initialized yet, it is created by streaming the token array
     * and collecting each token as a singleton list.
     *
     * @return the list of child token lists.
     */
    @Override
    public List<List<Token>> childrenTokens() {
        if (childrenTokens == null) {
            childrenTokens = Arrays.stream(tokens.toArray()).map(Collections::singletonList).collect(Collectors.toList());
        }
        return childrenTokens;
    }

    /**
     * Gets the array of elements (node structures) represented by this path node.
     * If the array has not been initialized yet, it is created with the size equal to the number of tokens.
     *
     * @return the array of elements.
     */
    Node[] elements() {
        if (elements == null) {
            elements = new Node[tokens.size()];
        }
        return elements;
    }

    /**
     * Retrieves the node associated with the specified key.
     *
     * @param key the key used to retrieve the node.
     * @return the node at the given key.
     */
    @Override
    public Node getNode(Object key) {
        return this.getNodeAt(Integer.valueOf((String) key));
    }

    /**
     * Retrieves the node at the specified index.
     *
     * @param index the index of the node to retrieve.
     * @return the node at the given index.
     */
    public Node getNodeAt(int index) {
        Node element = elements()[index];
        if (element == null) {
            List<Token> tokens = Collections.singletonList(this.tokens.get(index));
            elements()[index] = NodeUtils.createNode(tokens, source, false);
        }
        return elements()[index];
    }

    /**
     * Retrieves the path index value at the specified index.
     *
     * @param index the index of the path to retrieve.
     * @return the path index value at the given index.
     */
    public int getPathIndexValue(int index) {
        return getIndexNode(index).intValue();
    }

    /**
     * Retrieves the path key value at the specified index.
     *
     * @param index the index of the path to retrieve.
     * @return the path key value at the given index.
     */
    public String getPathKeyValue(int index) {
        return getKeyNode(index).toString();
    }

    /**
     * Retrieves the key node at the specified index.
     *
     * @param index the index of the key node to retrieve.
     * @return the key node at the given index.
     */
    public KeyPathNode getKeyNode(int index) {
        return (KeyPathNode) getNodeAt(index);
    }

    /**
     * Retrieves the index node at the specified index.
     *
     * @param index the index of the index node to retrieve.
     * @return the index node at the given index.
     */
    public IndexPathNode getIndexNode(int index) {
        return (IndexPathNode) getNodeAt(index);
    }

    /**
     * Retrieves the number of elements (node structures) represented by this path node.
     *
     * @return the number of elements.
     */
    public int length() {
        return elements().length;
    }

    /**
     * Returns the type of this node, which is NodeType.PATH.
     *
     * @return the NodeType.PATH enum.
     */
    @Override
    public NodeType type() {
        return NodeType.PATH;
    }

    /**
     * Retrieves the list of tokens associated with this path node.
     *
     * @return the list of tokens.
     */
    @Override
    public List<Token> tokens() {
        return tokens;
    }

    /**
     * Retrieves the root token of this path node.
     *
     * @return the root token.
     */
    @Override
    public Token rootElementToken() {
        return rootToken;
    }

    /**
     * Retrieves the character source associated with this path node.
     *
     * @return the character source.
     */
    @Override
    public CharSource charSource() {
        return source;
    }


    /**
     * Retrieves the path element at the specified index.
     *
     * @param index the index of the path element to retrieve.
     * @return the path element at the given index.
     */
    @Override
    public PathElement get(int index) {
        return (PathElement) getNodeAt(index);
    }

    /**
     * Checks if the specified object is equal to this path node.
     * Equality is based on the size and string representation of the tokens.
     *
     * @param o the object to compare with this path node.
     * @return true if the specified object is equal to this path node, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PathNode)) return false;

        final PathNode other = (PathNode) o;

        if (this.tokens.size() != other.tokens.size()) {
            return false;
        }

        for (int index = 0; index < this.tokens.size(); index++) {
            Token thisValue = this.tokens.get(index);
            Token otherValue = other.tokens.get(index);
            if (otherValue == null && thisValue == null) continue;
            String thisStr = thisValue.asString(this.source);
            String otherStr = otherValue.asString(other.source);
            if (!thisStr.equals(otherStr)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Computes and retrieves the hash code of this path node.
     * The hash code is computed based on the string representation of the tokens.
     * Once computed, it is stored for subsequent calls.
     *
     * @return the hash code of this path node.
     */
    @Override
    public int hashCode() {
        if (hashCodeSet) {
            return hashCode;
        }
        hashCode = Objects.hash(tokens.stream().map(tok -> tok.asString(this.source)).collect(Collectors.toList()));
        hashCodeSet = true;
        return hashCode;
    }


    /**
     * Retrieves the size of this path node, represented by the number of tokens.
     *
     * @return the size of this path node.
     */
    @Override
    public int size() {
        return tokens().size();
    }

    /**
     * Provides an iterator over the path elements of this path node.
     *
     * @return an iterator over the path elements.
     */
    @Override
    public Iterator<PathElement> iterator() {

        return new Iterator<PathElement>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < tokens().size();
            }

            @Override
            public PathElement next() {
                return (PathElement) getNodeAt(index++);
            }
        };
    }
}

```

###### PathElement.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.path;

import io.nats.jparse.node.ScalarNode;

/**
 * An interface for representing elements of a JSON Path expression.
 *
 * <p>Implementing classes must provide methods for determining whether an element is an index or a key, as well as
 * methods for converting an element to its index or key representation.</p>
 */
public interface PathElement extends ScalarNode {

    /**
     * Determines whether this element is an index.
     *
     * @return `true` if this element is an index, `false` otherwise
     */
    boolean isIndex();

    /**
     * Determines whether this element is a key.
     *
     * @return `true` if this element is a key, `false` otherwise
     */
    boolean isKey();

    /**
     * Returns this element as an index.
     *
     * @return this element as an `IndexPathNode`
     */
    default IndexPathNode asIndex() {
        return (IndexPathNode) this;
    }

    /**
     * Returns this element as a key.
     *
     * @return this element as a `KeyPathNode`
     */
    default KeyPathNode asKey() {
        return (KeyPathNode) this;
    }
}

```

###### PathParser.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.path;

import io.nats.jparse.node.RootNode;
import io.nats.jparse.node.support.ParseConstants;
import io.nats.jparse.node.support.TokenList;
import io.nats.jparse.parser.JsonParser;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.token.Token;
import io.nats.jparse.token.TokenTypes;

import java.util.List;

/**
 * Provides similar functionality for parsing Json Pats as JSONPath expressions.
 *
 */
public class PathParser implements JsonParser {


    /**
     * Construct a new path parser.
     */
    public PathParser() {
    }
    /**
     * Scans a given character source for tokens representing a JSONPath expression.
     *
     * @param source the character source to scan
     * @return a list of tokens representing the JSONPath expression
     */
    @Override
    public List<Token> scan(final CharSource source) {
        return scan(source, new TokenList());
    }

    /**
     * Compiles a given JSON Path expression into a parse tree represented by a `RootNode`.
     *
     * @param source the character source containing the JSONPath expression
     * @return a `RootNode` representing the parse tree for the JSONPath expression
     */
    @Override
    public RootNode parse(CharSource source) {
        return new RootNode((TokenList) scan(source), source, true);
    }

    private List<Token> scan(CharSource source, TokenList tokens) {

        char ch = ' ';

        loop:
        while (true) {

            ch = (char) source.next();

            switch (ch) {

                case ParseConstants.INDEX_BRACKET_START_TOKEN:
                    parseIndexOrKey(source, (char) source.next(), tokens);
                    break;

                case ParseConstants.A: //'A';
                case ParseConstants.B: //'B';
                case ParseConstants.C: //'C';
                case ParseConstants.D: //'D';
                case ParseConstants.E: //'E';
                case ParseConstants.F: //'F';
                case ParseConstants.G: //'G';
                case ParseConstants.H: //'H';
                case ParseConstants.I: //'I';
                case ParseConstants.J: //'J';
                case ParseConstants.K: //'K';
                case ParseConstants.L: //'L';
                case ParseConstants.M: //'M';
                case ParseConstants.N: //'N';
                case ParseConstants.O: //'O';
                case ParseConstants.P: //'P';
                case ParseConstants.Q: //'Q';
                case ParseConstants.R: //'R';
                case ParseConstants.S: //'S';
                case ParseConstants.T: //'T';
                case ParseConstants.U: //'U';
                case ParseConstants.V: //'V';
                case ParseConstants.W: //'W';
                case ParseConstants.X: //'X';
                case ParseConstants.Y: //'Y';
                case ParseConstants.Z: //'Z';
                case ParseConstants.A_: // = 'a';
                case ParseConstants.B_: //'b';
                case ParseConstants.C_: //'c';
                case ParseConstants.D_: //'d';
                case ParseConstants.E_: //'e';
                case ParseConstants.F_: //'f';
                case ParseConstants.G_: //'g';
                case ParseConstants.H_: //'h';
                case ParseConstants.I_: //'i';
                case ParseConstants.J_: //'j';
                case ParseConstants.K_: //'k';
                case ParseConstants.L_: //'l';
                case ParseConstants.M_: //'m';
                case ParseConstants.N_: //'n';
                case ParseConstants.O_: //'o';
                case ParseConstants.P_: //'p';
                case ParseConstants.Q_: //'q';
                case ParseConstants.R_: //'r';
                case ParseConstants.S_: //'s';
                case ParseConstants.T_: //'t';
                case ParseConstants.U_: //'u';
                case ParseConstants.V_: //'v';
                case ParseConstants.W_: //'w';
                case ParseConstants.X_: //'x';
                case ParseConstants.Y_: //'y';
                case ParseConstants.Z_: //'z';
                    parseKeyName(source, ch, tokens);
                    break;


                case ParseConstants.DOT:
                    parseKeyName(source, (char) source.next(), tokens);
                    break;

                case ParseConstants.ETX:
                    break loop;

                default:
                    throw new IllegalStateException("Unable to understand char " + ch + " index " + source.getIndex());


            }

        }
        return tokens;
    }


    private void parseIndexOrKey(CharSource source, char ch, TokenList tokens) {

        final int startIndex = source.getIndex();


        switch (ch) {

            case ParseConstants.NUM_0:
            case ParseConstants.NUM_1:
            case ParseConstants.NUM_2:
            case ParseConstants.NUM_3:
            case ParseConstants.NUM_4:
            case ParseConstants.NUM_5:
            case ParseConstants.NUM_6:
            case ParseConstants.NUM_7:
            case ParseConstants.NUM_8:
            case ParseConstants.NUM_9:
                parseIndex(source, startIndex, tokens, ch);
                break;


            case ParseConstants.SINGLE_QUOTE:
                parseKeyWithQuotes(source, startIndex + 1, tokens, ch);
                break;

            case ParseConstants.ETX:
                throw new IllegalStateException("reached end");


            default:
                throw new IllegalStateException("Unable to understand char " + ch + " index " + source.getIndex());
        }


    }

    private void parseKeyWithQuotes(CharSource source, int startIndex, TokenList tokens, char ch) {


        loop:
        while (true) {

            ch = (char) source.next();

            switch (ch) {


                case ParseConstants.A: //'A';
                case ParseConstants.B: //'B';
                case ParseConstants.C: //'C';
                case ParseConstants.D: //'D';
                case ParseConstants.E: //'E';
                case ParseConstants.F: //'F';
                case ParseConstants.G: //'G';
                case ParseConstants.H: //'H';
                case ParseConstants.I: //'I';
                case ParseConstants.J: //'J';
                case ParseConstants.K: //'K';
                case ParseConstants.L: //'L';
                case ParseConstants.M: //'M';
                case ParseConstants.N: //'N';
                case ParseConstants.O: //'O';
                case ParseConstants.P: //'P';
                case ParseConstants.Q: //'Q';
                case ParseConstants.R: //'R';
                case ParseConstants.S: //'S';
                case ParseConstants.T: //'T';
                case ParseConstants.U: //'U';
                case ParseConstants.V: //'V';
                case ParseConstants.W: //'W';
                case ParseConstants.X: //'X';
                case ParseConstants.Y: //'Y';
                case ParseConstants.Z: //'Z';
                case ParseConstants.A_: // = 'a';
                case ParseConstants.B_: //'b';
                case ParseConstants.C_: //'c';
                case ParseConstants.D_: //'d';
                case ParseConstants.E_: //'e';
                case ParseConstants.F_: //'f';
                case ParseConstants.G_: //'g';
                case ParseConstants.H_: //'h';
                case ParseConstants.I_: //'i';
                case ParseConstants.J_: //'j';
                case ParseConstants.K_: //'k';
                case ParseConstants.L_: //'l';
                case ParseConstants.M_: //'m';
                case ParseConstants.N_: //'n';
                case ParseConstants.O_: //'o';
                case ParseConstants.P_: //'p';
                case ParseConstants.Q_: //'q';
                case ParseConstants.R_: //'r';
                case ParseConstants.S_: //'s';
                case ParseConstants.T_: //'t';
                case ParseConstants.U_: //'u';
                case ParseConstants.V_: //'v';
                case ParseConstants.W_: //'w';
                case ParseConstants.X_: //'x';
                case ParseConstants.Y_: //'y';
                case ParseConstants.Z_: //'z';
                case ParseConstants.NEW_LINE_WS:
                case ParseConstants.CARRIAGE_RETURN_WS:
                case ParseConstants.TAB_WS:
                case ParseConstants.SPACE_WS:
                    continue;

                case ParseConstants.SINGLE_QUOTE:
                    break loop;

                case ParseConstants.ETX:
                    throw new IllegalStateException("reached end");


                default:
                    if (ch > 20 && ch < 127) {
                        break;
                    } else {
                        throw new IllegalStateException("Unable to understand char " + ch + " index " + source.getIndex());
                    }
            }
        }

        final int endIndex = source.getIndex();
        int i = source.nextSkipWhiteSpace();
        if (i == ParseConstants.INDEX_BRACKET_END_TOKEN) {
            tokens.add(new Token(startIndex, endIndex, TokenTypes.PATH_KEY_TOKEN));
        } else {
            throw new IllegalStateException("Unable to understand char " + ch + " index " + source.getIndex());
        }

    }


    private void parseKeyName(CharSource source, char ch, TokenList tokens) {
        final int startIndex = source.getIndex();

        loop:
        while (true) {

            ch = (char) source.next();
            switch (ch) {

                case ParseConstants.A: //'A';
                case ParseConstants.B: //'B';
                case ParseConstants.C: //'C';
                case ParseConstants.D: //'D';
                case ParseConstants.E: //'E';
                case ParseConstants.F: //'F';
                case ParseConstants.G: //'G';
                case ParseConstants.H: //'H';
                case ParseConstants.I: //'I';
                case ParseConstants.J: //'J';
                case ParseConstants.K: //'K';
                case ParseConstants.L: //'L';
                case ParseConstants.M: //'M';
                case ParseConstants.N: //'N';
                case ParseConstants.O: //'O';
                case ParseConstants.P: //'P';
                case ParseConstants.Q: //'Q';
                case ParseConstants.R: //'R';
                case ParseConstants.S: //'S';
                case ParseConstants.T: //'T';
                case ParseConstants.U: //'U';
                case ParseConstants.V: //'V';
                case ParseConstants.W: //'W';
                case ParseConstants.X: //'X';
                case ParseConstants.Y: //'Y';
                case ParseConstants.Z: //'Z';
                case ParseConstants.A_: // = 'a';
                case ParseConstants.B_: //'b';
                case ParseConstants.C_: //'c';
                case ParseConstants.D_: //'d';
                case ParseConstants.E_: //'e';
                case ParseConstants.F_: //'f';
                case ParseConstants.G_: //'g';
                case ParseConstants.H_: //'h';
                case ParseConstants.I_: //'i';
                case ParseConstants.J_: //'j';
                case ParseConstants.K_: //'k';
                case ParseConstants.L_: //'l';
                case ParseConstants.M_: //'m';
                case ParseConstants.N_: //'n';
                case ParseConstants.O_: //'o';
                case ParseConstants.P_: //'p';
                case ParseConstants.Q_: //'q';
                case ParseConstants.R_: //'r';
                case ParseConstants.S_: //'s';
                case ParseConstants.T_: //'t';
                case ParseConstants.U_: //'u';
                case ParseConstants.V_: //'v';
                case ParseConstants.W_: //'w';
                case ParseConstants.X_: //'x';
                case ParseConstants.Y_: //'y';
                case ParseConstants.Z_: //'z';
                    continue;

                case ParseConstants.ETX:
                    break loop;

                case ParseConstants.DOT:
                    break loop;

                case ParseConstants.INDEX_BRACKET_START_TOKEN:
                    final int endIndex = source.getIndex();
                    tokens.add(new Token(startIndex, endIndex, TokenTypes.PATH_KEY_TOKEN));
                    parseIndexOrKey(source, (char) source.next(), tokens);
                    return;


                default:
                    throw new IllegalStateException("Unable to understand char " + ch + " index " + source.getIndex());
            }
        }

        final int endIndex = source.getIndex();

        tokens.add(new Token(startIndex, endIndex, TokenTypes.PATH_KEY_TOKEN));


    }

    private void parseIndex(CharSource source, int startIndex, TokenList tokens, char ch) {
        loop:
        while (true) {

            ch = (char) source.next();
            switch (ch) {

                case ParseConstants.NUM_0:
                case ParseConstants.NUM_1:
                case ParseConstants.NUM_2:
                case ParseConstants.NUM_3:
                case ParseConstants.NUM_4:
                case ParseConstants.NUM_5:
                case ParseConstants.NUM_6:
                case ParseConstants.NUM_7:
                case ParseConstants.NUM_8:
                case ParseConstants.NUM_9:
                    break;

                case ParseConstants.INDEX_BRACKET_END_TOKEN:
                    break loop;


                case ParseConstants.ETX:
                    throw new IllegalStateException("reached end");


                default:
                    throw new IllegalStateException("Unable to understand char " + ch + " index " + source.getIndex());


            }
        }

        final int endIndex = source.getIndex();

        tokens.add(new Token(startIndex, endIndex, TokenTypes.PATH_INDEX_TOKEN));

    }
}

```

###### KeyPathNode.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.path;

import io.nats.jparse.node.NodeType;
import io.nats.jparse.node.ScalarNode;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.token.Token;

import java.util.Collections;
import java.util.List;

/**
 * Represents a key element of a JSON Path expression.
 * <p>
 * Contains methods for determining the type of element and for converting it to a key representation.
 */
public class KeyPathNode implements ScalarNode, PathElement {

    private final Token rootElementToken;
    private final CharSource charSource;


    /**
     * Construct KeyPathNode.
     * @param token token
     * @param charSource charSource
     */
    public KeyPathNode(final Token token, final CharSource charSource) {
        this.rootElementToken = token;
        this.charSource = charSource;
    }

    /**
     * Returns the type of this node.
     *
     * @return a `NodeType` representing this node's type
     */
    @Override
    public NodeType type() {
        return NodeType.PATH_KEY;
    }

    /**
     * Returns a list of tokens representing this node.
     *
     * @return a list of `Token`s representing this node
     */
    @Override
    public List<Token> tokens() {
        return Collections.singletonList(rootElementToken);
    }

    /**
     * Returns the root token of this node.
     *
     * @return the root `Token` of this node
     */
    @Override
    public Token rootElementToken() {
        return rootElementToken;
    }

    /**
     * Returns the character source of this node.
     *
     * @return the `CharSource` of this node
     */
    @Override
    public CharSource charSource() {
        return charSource;
    }

    /**
     * Returns the value of this node.
     *
     * @return a string representation of this node
     */
    @Override
    public Object value() {
        return toString();
    }

    /**
     * Determines whether this element is an index.
     *
     * @return `false`, since a `KeyPathNode` represents a key, not an index
     */
    @Override
    public boolean isIndex() {
        return false;
    }

    /**
     * Determines whether this element is a key.
     *
     * @return `true`, since a `KeyPathNode` represents a key
     */
    @Override
    public boolean isKey() {
        return true;
    }

    /**
     * Returns a string representation of this node.
     *
     * @return a string representation of this node
     */
    @Override
    public String toString() {
        return this.originalString();
    }

    /**
     * Returns a character sequence representation of this node.
     *
     * @return a character sequence representation of this node
     */
    public CharSequence toCharSequence() {
        return this.originalCharSequence();
    }
}


```

###### IndexPathNode.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.path;

import io.nats.jparse.node.NodeType;
import io.nats.jparse.node.ScalarNode;
import io.nats.jparse.node.support.CharSequenceUtils;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.token.Token;

import java.util.Collections;
import java.util.List;


/**
 * Represents a index element of a JSON Path expression.
 * <p>
 * Represents an index path node in the parse tree which also implements
 * the ScalarNode, CharSequence, and PathElement interfaces.
 */
public class IndexPathNode extends Number implements ScalarNode, CharSequence, PathElement {

    /**
     * The token associated with this path node.
     */
    private final Token token;

    /**
     * The source of characters where this path node comes from.
     */
    private final CharSource source;

    /**
     * A flag indicating whether the hashCode has been set for this path node.
     */
    private boolean hashCodeSet;

    /**
     * The hash code of this path node. This is computed and cached for performance.
     */
    private int hashCode;

    /**
     * Constructor for IndexPathNode.
     *
     * @param token  The token representing the node.
     * @param source The source of characters to be parsed.
     */
    public IndexPathNode(Token token, CharSource source) {
        this.token = token;
        this.source = source;
    }

    // Overridden Number class methods with their appropriate Javadoc comments

    /**
     * Returns the value of this node as an int.
     *
     * @return The int value represented by this object.
     */
    @Override
    public int intValue() {
        return source.getInt(token.startIndex, token.endIndex);
    }

    /**
     * Returns the value of this node as a long.
     *
     * @return The long value represented by this object.
     */
    @Override
    public long longValue() {
        return intValue();
    }

    /**
     * Returns the value of this node as a float.
     *
     * @return The float value represented by this object.
     */
    @Override
    public float floatValue() {
        return intValue();
    }

    /**
     * Returns the value of this node as a double.
     *
     * @return The double value represented by this object.
     */
    @Override
    public double doubleValue() {
        return intValue();
    }

    /**
     * Returns the type of the node.
     *
     * @return The type of the node.
     */
    @Override
    public NodeType type() {
        return NodeType.PATH_INDEX;
    }

    /**
     * Returns the value of the node.
     *
     * @return The value of the node.
     */
    @Override
    public Object value() {
        return intValue();
    }

    /**
     * Returns the list of tokens representing the node.
     *
     * @return The list of tokens representing the node.
     */
    @Override
    public List<Token> tokens() {
        return Collections.singletonList(this.token);
    }

    /**
     * Returns the root element token.
     *
     * @return The root element token.
     */
    @Override
    public Token rootElementToken() {
        return token;
    }

    /**
     * Returns the char source of the node.
     *
     * @return The char source of the node.
     */
    @Override
    public CharSource charSource() {
        return source;
    }

    /**
     * Indicates whether some other object is equal to this one.
     *
     * @param o The reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof CharSequence) {
            CharSequence other = (CharSequence) o;
            return CharSequenceUtils.equals(this, other);
        } else {
            return false;
        }
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        if (hashCodeSet) {
            return hashCode;
        }
        hashCode = CharSequenceUtils.hashCode(this);
        hashCodeSet = true;
        return hashCode;
    }

    /**
     * Indicates whether the node is an index.
     *
     * @return true if the node is an index; false otherwise.
     */
    @Override
    public boolean isIndex() {
        return true;
    }

    /**
     * Indicates whether the node is a key.
     *
     * @return true if the node is a key; false otherwise.
     */
    @Override
    public boolean isKey() {
        return false;
    }
}

```

##### source/

###### CharSource.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.source;

import io.nats.jparse.node.support.NumberParseResult;

import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * CharSource is a class that represents character sources used during JSON parsing. The JsonParser interface includes
 * methods for parsing and scanning CharSource objects, and the TokenEventListener interface includes methods that
 * take a CharSource object as a parameter. The Sources class is used to create CharSource objects from various input
 * sources, such as strings and files. CharSource is used to represent character sources, such as strings, and to
 * provide a consistent interface for working with those sources during JSON parsing.
 *
 * @see Sources
 * @see io.nats.jparse.token.TokenEventListener
 * @see io.nats.jparse.parser.JsonParser
 */
public interface CharSource {
    /**
     * Returns the next character in the source or ETX if there are no more characters.
     *
     * @return The next character in the source or ETX if there are no more characters
     */
    int next();

    /**
     * Returns the current index within the source.
     *
     * @return The current index within the source
     */
    int getIndex();

    /**
     * Returns the current character in the source.
     *
     * @return The current character in the source
     */
    char getCurrentChar();

    /**
     * Returns the current character in the source or ETX if there are no more characters.
     *
     * @return The current character in the source or ETX if there are no more characters
     */
    char getCurrentCharSafe();

    /**
     * Skips over whitespace characters in the source and returns the next non-whitespace character.
     *
     * @return The next non-whitespace character in the source
     */
    char skipWhiteSpace();

    /**
     * Returns the character at the given index in the source.
     *
     * @param index The index of the character to retrieve
     * @return The character at the given index in the source
     */
    char getChartAt(int index);

    /**
     * Returns the string containing characters from the source between the given start and end indices.
     *
     * @param startIndex The index of the first character to retrieve
     * @param endIndex   The index of the last character to retrieve
     * @return The string containing characters from the source between the given start and end indices
     */
    String getString(int startIndex, int endIndex);

    /**
     * Parses a double value from the characters in the source between the given start and end indices.
     *
     * @param startIndex The index of the first character to parse
     * @param endIndex   The index of the last character to parse
     * @return The double value parsed from the characters in the source between the given start and end indices
     */
    double getDouble(int startIndex, int endIndex);

    /**
     * Parses a float value from the characters in the source between the given start and end indices.
     *
     * @param startIndex The index of the first character to parse
     * @param endIndex   The index of the last character to parse
     * @return The float value parsed from the characters in the source between the given start and end indices
     */
    float getFloat(int startIndex, int endIndex);

    /**
     * Parses an integer value from the characters in the source between the given start and end indices.
     *
     * @param startIndex The index of the first character to parse
     * @param endIndex   The index of the last character to parse
     * @return The integer value parsed from the characters in the source between the given start and end indices
     */
    int getInt(int startIndex, int endIndex);

    /**
     * Parses a long value from the characters in the source between the given start and end indices.
     *
     * @param startIndex The index of the first character to parse
     * @param endIndex   The index of the last character to parse
     * @return The long value parsed from the characters in the source between the given start and end indices
     */
    long getLong(int startIndex, int endIndex);

    /**
     * Returns a character sequence containing characters from the source between the given start and end indices.
     *
     * @param startIndex The index of the first character to retrieve
     * @param endIndex   The index of the last character to retrieve
     * @return A character sequence containing characters from the source between the given start and end indices
     */
    CharSequence getCharSequence(int startIndex, int endIndex);

    /**
     * Returns a character array containing characters from the source between the given start and end indices.
     *
     * @param startIndex The index of the first character to retrieve
     * @param endIndex   The index of the last character to retrieve
     * @return A character array containing characters from the source between the given start and end indices
     */
    char[] getArray(int startIndex, int endIndex);

    /**
     * Returns an encoded string containing characters from the source between the given start and end indices.
     *
     * @param start The index of the first character to include in the encoded string
     * @param end   The index of the last character to include in the encoded string
     * @return An encoded string containing characters from the source between the given start and end indices
     */
    String getEncodedString(int start, int end);

    /**
     * Returns an encoded string containing characters from the source between the given start and end indices,
     * or a plain string if no encoding is necessary.
     *
     * @param start The index of the first character to include in the encoded string
     * @param end   The index of the last character to include in the encoded string
     * @return An encoded or plain string containing characters from the source between the given start and end indices
     */
    String toEncodedStringIfNeeded(int start, int end);

    /**
     * Parses a BigDecimal value from the characters in the source between the given start and end indices.
     *
     * @param startIndex The index of the first character to parse
     * @param endIndex   The index of the last character to parse
     * @return The BigDecimal value parsed from the characters in the source between the given start and end indices
     */
    BigDecimal getBigDecimal(int startIndex, int endIndex);

    /**
     * Parses a BigInteger value from the characters in the source between the given start and end indices.
     *
     * @param startIndex The index of the first character to parse
     * @param endIndex   The index of the last character to parse
     * @return The BigInteger value parsed from the characters in the source between the given start and end indices
     */
    BigInteger getBigInteger(int startIndex, int endIndex);

    /**
     * Finds the end index of an encoded string in the source, starting from the current index.
     *
     * @return The index of the last character in the encoded string.
     */
    int findEndOfEncodedString();

    /**
     * Finds the end index of a string in the source, starting from the current index.
     *
     * @return The index of the last character in the string.
     */
    int findEndString();

    /**
     * Parses a number from the source, starting from the current index.
     *
     * @return A `NumberParseResult` object containing the number and its end index
     */
    NumberParseResult findEndOfNumber();

    /**
     * Finds the end index of a JSON false value in the source, starting from the current index.
     *
     * @return The index of the last character in the false value
     */
    int findFalseEnd();

    /**
     * Finds the end index of a JSON true value in the source, starting from the current index.
     *
     * @return The index of the last character in the true value
     */
    int findTrueEnd();

    /**
     * Finds the end index of a JSON null value in the source, starting from the current index.
     *
     * @return The index of the last character in the null value
     */
    int findNullEnd();

    /**
     * Checks if the characters in the source between the given start and end indices match a given character sequence.
     *
     * @param startIndex The index of the first character to compare
     * @param endIndex   The index of the last character to compare
     * @param key        The character sequence to compare the source to
     * @return `true` if the characters in the source match the character sequence, otherwise `false`
     */
    boolean matchChars(int startIndex, int endIndex, CharSequence key);

    /**
     * Checks if the characters in the source between the given start and end indices represent an integer value.
     *
     * @param startIndex The index of the first character to check
     * @param endIndex   The index of the last character to check
     * @return `true` if the characters represent an integer value, otherwise `false`
     */
    boolean isInteger(int startIndex, int endIndex);

    /**
     * Skips over whitespace characters in the source and returns the index of the next non-whitespace character.
     *
     * @return The index of the next non-whitespace character in the source
     */
    int nextSkipWhiteSpace();

    /**
     * Returns a string containing error details for the given message, index, and character.
     *
     * @param message The error message to include in the details
     * @param index   The index in the source where the error occurred
     * @param ch      The character causing the error
     * @return A string containing error details for the given message, index, and character
     */
    String errorDetails(String message, int index, int ch);

    /**
     * Finds the next comma or end of array marker in the source, starting from the current index.
     *
     * @return `true` if a comma or end of array marker is found, otherwise `false`
     */
    boolean findCommaOrEndForArray();

    /**
     * Finds the end of an object or the separator between attributes, starting from the current index.
     *
     * @return `true` if the end of an object or the separator between attributes is found, otherwise `false`
     */
    boolean findObjectEndOrAttributeSep();

    /**
     * Checks the source for invalid characters and throws an exception if any are found.
     */
    void checkForJunk();

    /**
     * Parses a number from the source, starting from the current index, using a faster algorithm than `findEndOfNumber`.
     *
     * @return A `NumberParseResult` object containing the number and its end index
     */
    NumberParseResult findEndOfNumberFast();

    /**
     * Finds the end index of an encoded string in the source, starting from the current index, using a faster
     * algorithm than `findEndOfEncodedString`.
     *
     * @return The index of the last character in the encoded string.
     */
    int findEndOfEncodedStringFast();

    /**
     * Finds the next occurrence of a given character in the source, starting from the current index.
     *
     * @param c The character to search for
     * @return `true` if the character is found, otherwise `false`
     */
    boolean findChar(char c);

    /**
     * Finds the end of an attribute in the source, starting from the current index.
     *
     * @return The index of the last character in the attribute.
     */
    int findAttributeEnd();

}

```

###### CharArrayOffsetCharSource.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.source;

import io.nats.jparse.node.support.CharArrayUtils;
import io.nats.jparse.node.support.NumberParseResult;
import io.nats.jparse.node.support.ParseConstants;
import io.nats.jparse.source.support.CharArraySegment;
import io.nats.jparse.source.support.ParseDouble;
import io.nats.jparse.source.support.ParseFloat;
import io.nats.jparse.source.support.UnexpectedCharacterException;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * The CharArrayOffsetCharSource class is a concrete implementation of the CharSource abstract class.
 * It represents a character source backed by a char array, with an offset indicating the starting position
 * of the character sequence in the array. The class provides methods for parsing int, long, float, and double
 * values from the character sequence, as well as for checking whether a substring represents a valid integer.
 * It also provides an implementation of the errorDetails method, which generates a detailed error message
 * based on the current character being processed and the current parsing state.
 */
public class CharArrayOffsetCharSource implements CharSource, ParseConstants {

    private final static char[] MIN_INT_CHARS = MIN_INT_STR.toCharArray();
    private final static char[] MAX_INT_CHARS = MAX_INT_STR.toCharArray();
    private final char[] data;
    private final int sourceStartIndex;
    private final int sourceEndIndex;
    private final int length;
    private int index;

    /**
     * Create char source from offset into array
     * @param startIndex start index
     * @param endIndex end index
     * @param chars characters in source
     */
    public CharArrayOffsetCharSource(final int startIndex, final int endIndex, final char[] chars) {
        index = startIndex - 1;
        data = chars;
        sourceStartIndex = startIndex;
        sourceEndIndex = endIndex;
        length = endIndex - startIndex;
    }

    /**
     * Create Debug description
     * @param c char we left off at
     * @return description of char.
     */
    public static String debugCharDescription(int c) {
        String charString;
        if (c == ' ') {
            charString = "[SPACE]";
        } else if (c == '\t') {
            charString = "[TAB]";
        } else if (c == '\n') {
            charString = "[NEWLINE]";
        } else if (c == ETX) {
            charString = "ETX";
        } else {
            charString = "'" + (char) c + "'";
        }

        charString = charString + " with an int value of " + c;
        return charString;
    }

    @Override
    public int next() {
        if (index + 1 >= sourceEndIndex) {
            index = sourceEndIndex;
            return ETX;
        }
        return data[++index];
    }

    @Override
    public int findAttributeEnd() {
        int index = this.index;
        final char[] data = this.data;
        final int end = this.sourceEndIndex;

        loop:
        for (; index < end; index++) {
            char ch = data[index];
            switch (ch) {
                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                case ATTRIBUTE_SEP:
                    this.index = index;
                    break loop;
            }
        }

        return index;
    }

    @Override
    public boolean findChar(char c) {
        int index = this.index;
        final char[] data = this.data;
        final int end = sourceEndIndex;

        for (; index < end; index++) {
            if (data[index] == c) {
                this.index = index;
                return true;
            }
        }
        return false;
    }

    @Override
    public void checkForJunk() {
        int index = this.index;
        final char[] data = this.data;
        final int end = this.sourceEndIndex;
        int ch = ETX;

        for (; index < end; index++) {
            ch = data[index];
            switch (ch) {
                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                    continue;
                default:
                    throw new UnexpectedCharacterException("Junk", "Unexpected extra characters", this);

            }
        }
    }

    @Override
    public int nextSkipWhiteSpace() {
        int index = this.index + 1;
        final char[] data = this.data;
        final int endIndex = sourceEndIndex;
        int ch = ETX;

        loop:
        for (; index < endIndex; index++) {
            ch = data[index];
            switch (ch) {
                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                    continue;
                default:
                    break loop;
            }
        }
        this.index = index;
        return index == endIndex ? ETX : ch;
    }

    @Override
    public char skipWhiteSpace() {
        int index = this.index;
        final char[] data = this.data;
        final int endIndex = sourceEndIndex;

        char ch;

        loop:
        for (; index < endIndex; index++) {
            ch = data[index];
            switch (ch) {
                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                    continue;
                default:
                    break loop;
            }
        }
        this.index = index;
        return data[index];
    }

    @Override
    public int getIndex() {
        return index - sourceStartIndex;
    }

    @Override
    public char getCurrentChar() {
        return data[index];
    }

    @Override
    public char getCurrentCharSafe() {
        if (index >= sourceEndIndex) {
            return ETX;
        }
        return data[index];
    }

    @Override
    public char getChartAt(final int index) {
        return data[index + sourceStartIndex];
    }

    @Override
    public String getString(int startIndex, int endIndex) {
        final int from = startIndex + sourceStartIndex;
        return new String(data, from, endIndex - startIndex);
    }

    @Override
    public CharSequence getCharSequence(final int startIndex, final int endIndex) {
        return new CharArraySegment(startIndex + sourceStartIndex, endIndex - startIndex, data);
    }

    @Override
    public char[] getArray(int startIndex, int endIndex) {
        final int length = endIndex - startIndex;
        char[] array = new char[length];
        System.arraycopy(data, startIndex + sourceStartIndex, array, 0, length);
        return array;
    }

    @Override
    public BigDecimal getBigDecimal(int startIndex, int endIndex) {
        return new BigDecimal(data, startIndex + sourceStartIndex, endIndex - startIndex);
    }

    @Override
    public BigInteger getBigInteger(int startIndex, int endIndex) {
        return getBigDecimal(startIndex, endIndex).toBigInteger();
    }

    @Override
    public String getEncodedString(int start, int end) {
        return CharArrayUtils.decodeJsonString(data, start + sourceStartIndex, end + sourceStartIndex);
    }

    @Override
    public String toEncodedStringIfNeeded(int startIndex, int endIndex) {
        final int start = startIndex + sourceStartIndex;
        final int end = endIndex + sourceStartIndex;

        if (CharArrayUtils.hasEscapeChar(data, start, end)) {
            return getEncodedString(startIndex, endIndex);
        } else {
            return this.getString(startIndex, endIndex);
        }
    }

    @Override
    public String toString() {
        return new String(data, sourceStartIndex, length);
    }

    @Override
    public NumberParseResult findEndOfNumberFast() {


        int i = index + 1;
        char ch = 0;
        final char[] data = this.data;
        final int endIndex = this.sourceEndIndex;
        for (; i < endIndex; i++) {

            ch = data[i];

            switch (ch) {

                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                case ATTRIBUTE_SEP:
                case ARRAY_SEP:
                case OBJECT_END_TOKEN:
                case ARRAY_END_TOKEN:
                    index = i;
                    return new NumberParseResult(i - sourceStartIndex, false);

                case NUM_0:
                case NUM_1:
                case NUM_2:
                case NUM_3:
                case NUM_4:
                case NUM_5:
                case NUM_6:
                case NUM_7:
                case NUM_8:
                case NUM_9:
                    break;

                case DECIMAL_POINT:
                    index = i;
                    return findEndOfFloatFast();


                case EXPONENT_MARKER:
                case EXPONENT_MARKER2:
                    index = i;
                    return parseFloatWithExponentFast();


                default:
                    throw new IllegalStateException("Unexpected character " + ch + " at index " + index);

            }

        }

        index = i;
        return new NumberParseResult(i - sourceStartIndex, false);

    }

    private NumberParseResult findEndOfFloatFast() {


        int i = index + 1;
        char ch = 0;
        final char[] data = this.data;
        final int endIndex = this.sourceEndIndex;

        for (; i < endIndex; i++) {
            ch = data[i];
            switch (ch) {

                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                case ATTRIBUTE_SEP:
                case ARRAY_SEP:
                case OBJECT_END_TOKEN:
                case ARRAY_END_TOKEN:
                    index = i;
                    return new NumberParseResult(i - sourceStartIndex, true);

                case NUM_0:
                case NUM_1:
                case NUM_2:
                case NUM_3:
                case NUM_4:
                case NUM_5:
                case NUM_6:
                case NUM_7:
                case NUM_8:
                case NUM_9:
                    break;

                case EXPONENT_MARKER:
                case EXPONENT_MARKER2:
                    index = i;
                    return parseFloatWithExponentFast();


                default:
                    throw new UnexpectedCharacterException("Parsing JSON Float Number", "Unexpected character", this, ch, i);

            }

        }


        index = i;
        return new NumberParseResult(i - sourceStartIndex, true);

    }

    private NumberParseResult parseFloatWithExponentFast() {

        int i = index + 1;
        char ch = 0;
        int signOperator = 0;
        final char[] data = this.data;
        final int end = sourceEndIndex;
        for (; i < end; i++) {
            ch = data[i];

            switch (ch) {

                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                case ATTRIBUTE_SEP:
                case ARRAY_SEP:
                case OBJECT_END_TOKEN:
                case ARRAY_END_TOKEN:
                    index = i;
                    return new NumberParseResult(i - sourceStartIndex, true);

                case MINUS:
                case PLUS:
                    signOperator++;
                    if (signOperator > 1) {
                        throw new IllegalStateException("Too many sign operators when parsing exponent of float");
                    }
                    break;

                case NUM_0:
                case NUM_1:
                case NUM_2:
                case NUM_3:
                case NUM_4:
                case NUM_5:
                case NUM_6:
                case NUM_7:
                case NUM_8:
                case NUM_9:
                    break;


                default:
                    throw new IllegalStateException("Unexpected character " + ch + " at index " + index);

            }

        }


        index = i;
        return new NumberParseResult(i - sourceStartIndex, true);

    }

    @Override
    public int findEndOfEncodedStringFast() {
        int i = ++index;
        final char[] data = this.data;
        final int end = sourceEndIndex;
        boolean controlChar = false;
        for (; i < end; i++) {
            char ch = data[i];
            switch (ch) {
                case CONTROL_ESCAPE_TOKEN:
                    controlChar = !controlChar;
                    continue;
                case STRING_END_TOKEN:
                    if (!controlChar) {
                        index = i + 1;
                        return i;
                    }
                    controlChar = false;
                    break;
                default:
                    controlChar = false;
                    break;

            }
        }
        throw new IllegalStateException("Unable to find closing for String");
    }

    private int findEndOfStringControlEncode(int i) {
        final char[] data = this.data;
        final int length = data.length;
        char ch = 0;


        ch = data[i];
        switch (ch) {
            case CONTROL_ESCAPE_TOKEN:
            case STRING_END_TOKEN:
            case 'n':
            case 'b':
            case '/':
            case 'r':
            case 't':
            case 'f':
                return i;

            case 'u':
                return findEndOfHexEncoding(i);

            default:
                throw new UnexpectedCharacterException("Parsing JSON String", "Unexpected character while finding closing for String", this, ch, i);

        }

    }

    @Override
    public int findEndOfEncodedString() {
        int i = ++index;
        final char[] data = this.data;
        final int length = data.length;
        char ch = 0;
        for (; i < length; i++) {
            ch = data[i];
            switch (ch) {
                case CONTROL_ESCAPE_TOKEN:
                    i = findEndOfStringControlEncode(i + 1);
                    continue;
                case STRING_END_TOKEN:
                    index = i + 1;
                    return i;
                default:
                    if (ch >= SPACE_WS) {
                        continue;
                    }
                    throw new UnexpectedCharacterException("Parsing JSON String", "Unexpected character while finding closing for String", this, ch, i);

            }
        }

        throw new UnexpectedCharacterException("Parsing JSON Encoded String", "Unable to find closing for String", this, ch, i);
    }

    private int findEndOfHexEncoding(int index) {
        final char[] data = this.data;
        final int length = data.length;

        if (isHex(data[++index]) && isHex(data[++index]) && isHex(data[++index]) && isHex(data[++index])) {
            return index;
        } else {
            throw new UnexpectedCharacterException("Parsing hex encoding in a string", "Unexpected character", this);
        }

    }

    private boolean isHex(char datum) {
        switch (datum) {
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
            default:
                return false;
        }
    }

    @Override
    public int findEndString() {

        int i = ++index;
        final char[] data = this.data;
        final int length = data.length;
        char ch = 0;

        for (; i < length; i++) {
           ch = data[i];
            switch (ch) {
                case STRING_END_TOKEN:
                    index = i;
                    return i;
                default:
                    if (ch >= SPACE_WS) {
                        continue;
                    }
                    throw new UnexpectedCharacterException("Parsing JSON String", "Unexpected character while finding closing for String", this,  ch, i);
            }
        }
        throw new UnexpectedCharacterException("Parsing JSON String", "Unable to find closing for String", this,  ch, i);
    }

    @Override
    public NumberParseResult findEndOfNumber() {

        final char startCh = getCurrentChar();
        final int startIndex = index;
        char ch = startCh;


        int i = index + 1;

        final char[] data = this.data;
        final int endIndex = this.sourceEndIndex;

        loop:
        for (; i < endIndex; i++) {

            ch = data[i];

            switch (ch) {

                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                case ATTRIBUTE_SEP:
                case ARRAY_SEP:
                case OBJECT_END_TOKEN:
                case ARRAY_END_TOKEN:
                    break loop;

                case NUM_0:
                case NUM_1:
                case NUM_2:
                case NUM_3:
                case NUM_4:
                case NUM_5:
                case NUM_6:
                case NUM_7:
                case NUM_8:
                case NUM_9:
                    break;

                case DECIMAL_POINT:

                    if (startCh == MINUS) {
                        final int numLenSoFar = i - startIndex;
                        if (numLenSoFar == 1) {
                            throw new UnexpectedCharacterException("Parsing JSON Number", "Unexpected character", this, ch, i);
                        }
                    }
                    index = i;
                    return findEndOfFloat();


                case EXPONENT_MARKER:
                case EXPONENT_MARKER2:
                    index = i;
                    return parseFloatWithExponent();


                default:
                    throw new UnexpectedCharacterException("Parsing JSON Number", "Unexpected character", this, ch, i);

            }

        }

        index = i;
        final int numLength = i - startIndex;

        switch (startCh) {
            case NUM_0:
                if (numLength != 1) {
                    throw new UnexpectedCharacterException("Parsing JSON Int Number",
                            "Int can't start with a 0 ", this, startCh, startIndex);
                }
                break;
            case PLUS:
                throw new UnexpectedCharacterException("Parsing JSON Int Number",
                        "Int can't start with a plus ", this, startCh, startIndex);

            case MINUS:
                switch (numLength) {
                    case 1:
                        throw new UnexpectedCharacterException("Parsing JSON Int Number",
                                "Int can't be only a minus, number is missing", this, startCh, startIndex);
                    case 2:
                        break;
                    default:
                        if (data[startIndex + 1] == NUM_0) {

                            throw new UnexpectedCharacterException("Parsing JSON Int Number",
                                    "0 can't be after minus sign", this, startCh, startIndex);
                        }
                }
        }
        return new NumberParseResult(i - this.sourceStartIndex, false);
    }

    private NumberParseResult findEndOfFloat() {

        int i = index + 1;
        char ch = (char) next();

        if (!isNumber(ch)) {
            throw new UnexpectedCharacterException("Parsing float part of number", "After decimal point expecting number but got", this, ch, this.index);
        }
        final char[] data = this.data;

        final int endIndex = this.sourceEndIndex;

        for (; i < endIndex; i++) {
            ch = data[i];
            switch (ch) {

                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                case ATTRIBUTE_SEP:
                case ARRAY_SEP:
                case OBJECT_END_TOKEN:
                case ARRAY_END_TOKEN:
                    index = i;
                    return new NumberParseResult(i - sourceStartIndex, true);

                case NUM_0:
                case NUM_1:
                case NUM_2:
                case NUM_3:
                case NUM_4:
                case NUM_5:
                case NUM_6:
                case NUM_7:
                case NUM_8:
                case NUM_9:
                    break;

                case EXPONENT_MARKER:
                case EXPONENT_MARKER2:
                    index = i;
                    return parseFloatWithExponent();


                default:
                    throw new UnexpectedCharacterException("Parsing JSON Float Number", "Unexpected character", this, ch, i);

            }

        }


        index = i;
        return new NumberParseResult(i - sourceStartIndex, true);

    }

    private boolean isNumber(final char ch) {
        switch (ch) {
            case NUM_0:
            case NUM_1:
            case NUM_2:
            case NUM_3:
            case NUM_4:
            case NUM_5:
            case NUM_6:
            case NUM_7:
            case NUM_8:
            case NUM_9:
                return true;
            default:
                return false;
        }
    }

    private NumberParseResult parseFloatWithExponent() {


        char ch = (char) next();
        if (!isNumberOrSign(ch)) {
            throw new UnexpectedCharacterException("Parsing exponent part of float", "After exponent expecting number or sign but got", this, ch, this.index);
        }

        if (isSign(ch)) {
            ch = (char) next();
            if (!isNumber(ch)) {
                throw new UnexpectedCharacterException("Parsing exponent part of float after sign", "After sign expecting number but got", this, ch, this.index);
            }
        }

        int i = index + 1;
        final char[] data = this.data;

        final int endIndex = this.sourceEndIndex;

        for (; i < endIndex; i++) {
            ch = data[i];

            switch (ch) {

                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                case ATTRIBUTE_SEP:
                case ARRAY_SEP:
                case OBJECT_END_TOKEN:
                case ARRAY_END_TOKEN:
                    index = i;
                    return new NumberParseResult(i - sourceStartIndex, true);

                case NUM_0:
                case NUM_1:
                case NUM_2:
                case NUM_3:
                case NUM_4:
                case NUM_5:
                case NUM_6:
                case NUM_7:
                case NUM_8:
                case NUM_9:
                    break;

                default:
                    throw new UnexpectedCharacterException("Parsing Float with exponent", "Unable to find closing for Number", this, ch, i);

            }
        }
        index = i;
        return new NumberParseResult(i - sourceStartIndex, true);
    }

    private boolean isNumberOrSign(char ch) {
        switch (ch) {
            case NUM_0:
            case NUM_1:
            case NUM_2:
            case NUM_3:
            case NUM_4:
            case NUM_5:
            case NUM_6:
            case NUM_7:
            case NUM_8:
            case NUM_9:
            case MINUS:
            case PLUS:
                return true;
            default:
                return false;
        }
    }

    private boolean isSign(char ch) {
        switch (ch) {
            case MINUS:
            case PLUS:
                return true;
            default:
                return false;
        }
    }

    @Override
    public int findFalseEnd() {

        if (this.data[++index] == 'a' && this.data[++index] == 'l' && this.data[++index] == 's' && this.data[++index] == 'e') {
            return ++index - sourceStartIndex;
        } else {
            throw new UnexpectedCharacterException("Parsing JSON False Boolean", "Unexpected character", this);

        }
    }

    @Override
    public int findTrueEnd() {
        if (this.data[++index] == 'r' && this.data[++index] == 'u' && this.data[++index] == 'e') {
            return ++index - sourceStartIndex;
        } else {
            throw new UnexpectedCharacterException("Parsing JSON True Boolean", "Unexpected character", this);
        }
    }

    @Override
    public boolean findObjectEndOrAttributeSep() {
        int i = index;
        char ch = 0;
        final char[] data = this.data;
        final int end = sourceEndIndex;

        for (; i < end; i++) {
            ch = data[i];
            switch (ch) {
                case OBJECT_END_TOKEN:
                    this.index = i + 1;
                    return true;
                case ATTRIBUTE_SEP:
                    this.index = i;
                    return false;
            }
        }


        throw new UnexpectedCharacterException("Parsing Object Key", "Finding object end or separator", this);
    }

    @Override
    public boolean findCommaOrEndForArray() {
        int i = index;
        char ch = 0;
        final char[] data = this.data;
        final int end = sourceEndIndex;

        for (; i < end; i++) {
            ch = data[i];
            switch (ch) {
                case ARRAY_END_TOKEN:
                    this.index = i + 1;
                    return true;
                case ARRAY_SEP:
                    this.index = i;
                    return false;

                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                    continue;

                default:
                    throw new UnexpectedCharacterException("Parsing Object Key", "Finding object end or separator", this, ch, i);
            }
        }


        throw new UnexpectedCharacterException("Parsing Array", "Finding list end or separator", this);
    }

    @Override
    public int findNullEnd() {
        if (this.data[++index] == 'u' && this.data[++index] == 'l' && this.data[++index] == 'l') {
            return ++index - sourceStartIndex;
        } else {
            throw new UnexpectedCharacterException("Parsing JSON Null", "Unexpected character", this);
        }
    }

    @Override
    public boolean matchChars(final int startIndex, final int endIndex, CharSequence key) {

        final int length = endIndex - startIndex;
        final int offset = this.sourceStartIndex;
        int idx = startIndex + offset;


        switch (length) {
            case 1:
                return key.charAt(0) == data[idx];
            case 2:
                return key.charAt(0) == data[idx] &&
                        key.charAt(1) == data[idx + 1];
            case 3:
                return key.charAt(0) == data[idx] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(2) == data[idx + 2];
            case 4:
                return key.charAt(0) == data[idx] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(3) == data[idx + 3];

            case 5:
                return key.charAt(1) == data[idx + 1] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(0) == data[idx] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(4) == data[idx + 4];

            case 6:
                return key.charAt(0) == data[idx] &&
                        key.charAt(5) == data[idx + 5] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(4) == data[idx + 4];

            case 7:
                return key.charAt(0) == data[idx] &&
                        key.charAt(6) == data[idx + 6] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(5) == data[idx + 5] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(4) == data[idx + 4];

            case 8:
                return key.charAt(0) == data[idx] &&
                        key.charAt(7) == data[idx + 7] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(5) == data[idx + 5] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(6) == data[idx + 6] &&
                        key.charAt(4) == data[idx + 4];


            case 9:
                return key.charAt(0) == data[idx] &&
                        key.charAt(8) == data[idx + 8] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(6) == data[idx + 6] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(7) == data[idx + 7] &&
                        key.charAt(4) == data[idx + 4] &&
                        key.charAt(5) == data[idx + 5] &&
                        key.charAt(1) == data[idx + 1];

            case 10:
                return key.charAt(0) == data[idx] &&
                        key.charAt(9) == data[idx + 9] &&
                        key.charAt(6) == data[idx + 6] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(7) == data[idx + 7] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(4) == data[idx + 4] &&
                        key.charAt(5) == data[idx + 5] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(8) == data[idx + 8];

            case 11:
                return key.charAt(0) == data[idx] &&
                        key.charAt(10) == data[idx + 10] &&
                        key.charAt(6) == data[idx + 6] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(7) == data[idx + 7] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(9) == data[idx + 9] &&
                        key.charAt(4) == data[idx + 4] &&
                        key.charAt(5) == data[idx + 5] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(8) == data[idx + 8];

            case 12:
                return key.charAt(0) == data[idx] &&
                        key.charAt(11) == data[idx + 11] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(7) == data[idx + 7] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(6) == data[idx + 6] &&
                        key.charAt(9) == data[idx + 9] &&
                        key.charAt(4) == data[idx + 4] &&
                        key.charAt(5) == data[idx + 5] &&
                        key.charAt(10) == data[idx + 10] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(8) == data[idx + 8];

            default:
                final int start = 0;
                final int end = length - 1;
                final int middle = length / 2;

                if (key.charAt(start) == data[idx] &&
                        key.charAt(end) == data[idx + end] &&
                        key.charAt(middle) == data[idx + middle]) {
                    for (int i = 1; i < length; i++) {
                        if (key.charAt(i) != data[idx + i]) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    return false;
                }
        }

    }

    public boolean isInteger(int startIndex, int endIndex) {
        int len = endIndex - startIndex;
        int offset = this.sourceStartIndex;
        final char[] digitChars = data;
        final boolean negative = (digitChars[startIndex] == '-');
        final int cmpLen = negative ? MIN_INT_STR_LENGTH : MAX_INT_STR_LENGTH;
        if (len < cmpLen) return true;
        if (len > cmpLen) return false;
        final char[] cmpStr = negative ? MIN_INT_CHARS : MAX_INT_CHARS;
        for (int i = 0; i < cmpLen; ++i) {
            int diff = digitChars[startIndex + i + offset] - cmpStr[i];
            if (diff != 0) {
                return (diff < 0);
            }
        }
        return true;
    }

    @Override
    public double getDouble(final int startIndex, final int endIndex) {

        return ParseDouble.parseDouble(data, startIndex + sourceStartIndex, endIndex + sourceStartIndex);
    }

    @Override
    public float getFloat(int from, int to) {
        return ParseFloat.parseFloat(data, from + sourceStartIndex, to + sourceStartIndex);
    }

    @Override
    public int getInt(int startIndex, int endIndex) {

        int from = startIndex + sourceStartIndex;
        int to = endIndex + sourceStartIndex;

        final char[] digitChars = data;

        int num;
        boolean negative = false;
        char c = digitChars[from];
        if (c == '-') {
            from++;
            negative = true;
        } else if (c == '+') {
            from++;
            negative = false;
        }

        c = digitChars[from];
        num = (c - '0');
        from++;

        int digit;

        for (; from < to; from++) {
            c = digitChars[from];
            digit = (c - '0');
            num = (num * 10) + digit;
        }

        return negative ? num * -1 : num;

    }

    @Override
    public long getLong(final int startIndex, final int endIndex) {
        int from = startIndex + sourceStartIndex;
        int to = endIndex + sourceStartIndex;

        final char[] digitChars = data;

        long num;
        boolean negative = false;
        char c = digitChars[from];
        if (c == '-') {
            from++;
            negative = true;
        }

        c = digitChars[from];
        num = (c - '0');
        from++;

        long digit;

        for (; from < to; from++) {
            c = digitChars[from];
            digit = (c - '0');
            num = (num * 10) + digit;
        }

        return negative ? num * -1 : num;

    }

    @Override
    public String errorDetails(String message, int index, int ch) {
        StringBuilder buf = new StringBuilder(255);

        final char[] array = data;

        buf.append(message).append("\n");


        buf.append("\n");
        buf.append("The current character read is " + debugCharDescription(ch)).append('\n');


        int line = 0;
        int lastLineIndex = 0;

        for (int i = 0; i < index && i < array.length; i++) {
            if (array[i] == '\n') {
                line++;
                lastLineIndex = i + 1;
            }
        }

        int count = 0;

        for (int i = lastLineIndex; i < array.length; i++, count++) {
            if (array[i] == '\n') {
                break;
            }
        }


        buf.append("line number " + (line + 1)).append('\n');
        buf.append("index number " + index).append('\n');
        buf.append("offset index number " + index + sourceStartIndex).append('\n');


        try {
            buf.append(new String(array, lastLineIndex, count)).append('\n');
        } catch (Exception ex) {

            try {
                int start = index = (index - 10 < 0) ? 0 : index - 10;

                buf.append(new String(array, start, index)).append('\n');
            } catch (Exception ex2) {
                buf.append(new String(array)).append('\n');
            }
        }
        for (int i = 0; i < (index - lastLineIndex); i++) {
            buf.append('.');
        }
        buf.append('^');

        return buf.toString();
    }
}

```

###### Sources.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.source;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The `Sources` class provides utility methods for creating `CharSource` objects from various input sources, such as strings,
 * byte arrays, files, input streams, and readers.
 * <p>
 * The `Sources` class provides a set of utility methods that allow you to create `CharSource` objects from various
 * input sources. These sources include strings, byte arrays, files, input streams, and readers.
 * </p>
 * <p>
 * The `charSeqSource` method creates a `CharSource` object from a `CharSequence`. If the `CharSequence` is an
 * instance of `String`, it creates a `CharArrayCharSource` object with the `String` as input. Otherwise, it creates
 * a `CharArrayCharSource` object with the `CharSequence` converted to a `String`.
 * </p>
 * <p>
 * The `stringSource` method creates a `CharSource` object from a `String`. It does this by calling the `charSource`
 * method with the `String` converted to a `char` array.
 * </p>
 * <p>
 * The `byteSource` method creates a `CharSource` object from a byte array using a specified `Charset`. If a `Charset`
 * is not specified, it defaults to the UTF-8 `Charset`.
 * </p>
 * <p>
 * The `charSource` method creates a `CharSource` object from a `char` array. The `charSource` method has three
 * overloaded versions. One version creates a `CharSource` object from a `char` array with no offset. Another version creates a `CharSource` object from a `char` array with a specified offset. The third version creates a `CharSource` object from a `char` array with a specified offset and end index.
 * </p>
 * <p>
 * The `charBufferSource` method creates a `CharSource` object from a `CharBuffer` by calling the `charSeqSource`
 * method with the `CharBuffer` as input.
 * </p>
 * <p>
 * The `fileSource` method creates a `CharSource` object from a file. It has two overloaded versions, one of
 * which allows you to specify a `Charset`. If a `Charset` is not specified, it defaults to the UTF-8 `Charset`.
 * If there is an error reading the file, an `IllegalStateException` is thrown.
 * </p>
 * <p>
 * The `inputStreamSource` method creates a `CharSource` object from an input stream. It has two overloaded versions,
 * one of which allows you to specify a `Charset`. If a `Charset` is not specified, it defaults to the UTF-8 `Charset`.
 * </p>
 * <p>
 * The `readerSource` method creates a `CharSource` object from a `Reader`. It does this by reading the `Reader`
 * line by line and appending each line to a `StringBuilder`. The resulting `StringBuilder` is then used to create
 * a `CharArrayCharSource` object. If there is an error reading the `Reader`, an `IllegalStateException` is thrown.
 * </p>
 */
public class Sources {

    private Sources() {
    }

    /**
     * Creates a `CharSource` object from the specified `CharSequence`.
     *
     * @param source The input `CharSequence`
     * @return The resulting `CharSource` object
     */
    public static CharSource charSeqSource(final CharSequence source) {
        if (source instanceof String) {
            return new CharArrayCharSource((String) source);
        } else {
            return new CharArrayCharSource(source.toString());
        }
    }

    /**
     * Creates a `CharSource` object from the specified `String`.
     *
     * @param source The input `String`
     * @return The resulting `CharSource` object
     */
    public static CharSource stringSource(final String source) {
        return charSource(source.toCharArray());
    }

    /**
     * Creates a `CharSource` object from the specified byte array using the specified `Charset`.
     *
     * @param source  The input byte array
     * @param charset The `Charset` to use
     * @return The resulting `CharSource` object
     */
    public static CharSource byteSource(final byte[] source, final Charset charset) {
        return new CharArrayCharSource(new String(source, charset));
    }

    /**
     * Creates a `CharSource` object from the specified byte array using the UTF-8 `Charset`.
     *
     * @param source The input byte array
     * @return The resulting `CharSource` object
     */
    public static CharSource byteSource(final byte[] source) {
        return byteSource(source, StandardCharsets.UTF_8);
    }

    /**
     * Creates a `CharSource` object from the specified `char` array.
     *
     * @param source The input `char` array
     * @return The resulting `CharSource` object
     */
    public static CharSource charSource(final char[] source) {
        return new CharArrayCharSource(source);
    }

    /**
     * Creates a `CharSource` object from the specified `char` array with the specified offset.
     *
     * @param offset The offset to start reading from
     * @param source The input `char` array
     * @return The resulting `CharSource` object
     */
    public static CharSource charSource(final int offset, final char[] source) {
        return new CharArrayOffsetCharSource(offset, source.length, source);
    }

    /**
     * Creates a `CharSource` object from the specified `char` array with the specified offset and end index.
     *
     * @param offset   The offset to start reading from
     * @param endIndex The end index to stop reading at
     * @param source   The input `char` array
     * @return The resulting `CharSource` object
     */
    public static CharSource charSource(final int offset, final int endIndex, final char[] source) {
        return new CharArrayOffsetCharSource(offset, endIndex, source);
    }

    /**
     * Creates a `CharSource` object from the specified `CharBuffer`.
     *
     * @param source The input `CharBuffer`
     * @return The resulting `CharSource` object
     */
    public static CharSource charBufferSource(final CharBuffer source) {
        return charSeqSource(source);
    }

    /**
     * Creates a `CharSource` object from the specified file name.
     *
     * @param fileNameSource The file name to read from
     * @return The resulting `CharSource` object
     */
    public static CharSource fileSource(final String fileNameSource) {
        return fileSource(fileNameSource, StandardCharsets.UTF_8);
    }

    /**
     * Creates a `CharSource` object from the specified file name using the specified `Charset`.
     *
     * @param fileNameSource The file name to read from
     * @param charset        The `Charset` to use
     * @return The resulting `CharSource` object
     * @throws IllegalStateException if there is an error reading the file
     */
    public static CharSource fileSource(final String fileNameSource, final Charset charset) {
        try {
            return byteSource(Files.readAllBytes(Paths.get(fileNameSource)), charset);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Creates a `CharSource` object from the specified file.
     *
     * @param fileSource The file to read from
     * @return The resulting `CharSource` object
     */
    public static CharSource fileSource(final File fileSource) {
        return fileSource(fileSource, StandardCharsets.UTF_8);
    }

    /**
     * Creates a `CharSource` object from the specified file using the specified `Charset`.
     *
     * @param fileSource The file to read from
     * @param charset    The `Charset` to use
     * @return The resulting `CharSource` object
     * @throws IllegalStateException if there is an error reading the file
     */
    public static CharSource fileSource(final File fileSource, final Charset charset) {
        try {
            return byteSource(Files.readAllBytes(Paths.get(fileSource.getAbsolutePath())), charset);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Creates a `CharSource` object from the specified input stream using the specified `Charset`.
     *
     * @param inputStreamSource The input stream to read from
     * @param charset           The `Charset` to use
     * @return The resulting `CharSource` object
     */
    public static CharSource inputStreamSource(final InputStream inputStreamSource, final Charset charset) {
        return readerSource(new InputStreamReader(inputStreamSource, charset));
    }

    /**
     * Creates a `CharSource` object from the specified input stream using the UTF-8 `Charset`.
     *
     * @param inputStreamSource The input stream to read from
     * @return The resulting `CharSource` object
     */
    public static CharSource inputStreamSource(final InputStream inputStreamSource) {
        return inputStreamSource(inputStreamSource, StandardCharsets.UTF_8);
    }

    /**
     * Creates a `CharSource` object from the specified `Reader`.
     *
     * @param readerSource The `Reader` to read from
     * @return The resulting `CharSource` object
     */
    public static CharSource readerSource(final Reader readerSource) {
        final BufferedReader reader = new BufferedReader(readerSource);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String s = reader.readLine();
            while (s != null) {
                stringBuilder.append(s).append('\n');
                s = reader.readLine();
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        return new CharArrayCharSource(stringBuilder.toString());
    }
}

```

###### CharArrayCharSource.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.source;

import io.nats.jparse.node.support.CharArrayUtils;
import io.nats.jparse.node.support.NumberParseResult;
import io.nats.jparse.node.support.ParseConstants;
import io.nats.jparse.source.support.CharArraySegment;
import io.nats.jparse.source.support.ParseDouble;
import io.nats.jparse.source.support.ParseFloat;
import io.nats.jparse.source.support.UnexpectedCharacterException;

import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * This class is a char source for char arrays.
 * <p>
 * This class is not thread safe.
 * <p>
 * This class is used by the parser to parse strings.
 * <p>
 * This class is not a general purpose char source.
 * <p>
 * This class is used by the parser to parse strings.
 * <p>
 * This class is not a general purpose char source.
 */
public class CharArrayCharSource implements CharSource, ParseConstants {

    /**
     * The min length int as a string.
     */
    private final static char[] MIN_INT_CHARS = MIN_INT_STR.toCharArray();

    /**
     * The max int length as a string.
     */
    private final static char[] MAX_INT_CHARS = MAX_INT_STR.toCharArray();

    /**
     * The data of the char source.
     */
    private final char[] data;

    /** The index into the data. */
    private int index;


    /**
     * Construct it
     * @param chars for data source.
     */
    public CharArrayCharSource(final char[] chars) {
        index = -1;
        data = chars;
    }

    /**
     * Construct it
     * @param str for data source.
     */
    public CharArrayCharSource(final String str) {
        index = -1;
        data = str.toCharArray();
    }

    /**
     * Create Debug description
     * @param c char we left off at
     * @return description of char.
     */
    public static String debugCharDescription(int c) {
        String charString;
        if (c == ' ') {
            charString = "[SPACE]";
        } else if (c == '\t') {
            charString = "[TAB]";
        } else if (c == '\n') {
            charString = "[NEWLINE]";
        } else if (c == ETX) {
            charString = "ETX";
        } else {
            charString = "'" + (char) c + "'";
        }

        charString = charString + " with an int value of " + c;
        return charString;
    }

    @Override
    public int next() {
        if (index + 1 >= data.length) {
            index = data.length;
            return ETX;
        }
        return data[++index];
    }

    @Override
    public void checkForJunk() {
        int index = this.index;
        final char[] data = this.data;
        final int length = data.length;
        int ch = ETX;

        for (; index < length; index++) {
            ch = data[index];
            switch (ch) {
                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                    continue;
                default:
                    throw new UnexpectedCharacterException("Junk", "Unexpected extra characters", this);

            }
        }
    }

    @Override
    public int nextSkipWhiteSpace() {
        int index = this.index + 1;
        final char[] data = this.data;
        final int length = data.length;
        int ch = ETX;

        loop:
        for (; index < length; index++) {
            ch = data[index];
            switch (ch) {
                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                    continue;
                default:
                    break loop;
            }
        }
        this.index = index;
        return index == length ? ETX : ch;
    }

    @Override
    public char skipWhiteSpace() {
        int index = this.index;
        final char[] data = this.data;
        final int length = data.length;

        char ch;

        loop:
        for (; index < length; index++) {
            ch = data[index];
            switch (ch) {
                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                    continue;
                default:
                    break loop;
            }
        }
        this.index = index;
        return data[index];
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public char getCurrentChar() {
        return data[index];
    }

    @Override
    public char getCurrentCharSafe() {
        if (index >= data.length) {
            return ETX;
        }
        return data[index];
    }

    @Override
    public char getChartAt(int index) {
        return data[index];
    }

    @Override
    public String getString(int startIndex, int endIndex) {
        return new String(data, startIndex, endIndex - startIndex);
    }

    @Override
    public CharSequence getCharSequence(final int startIndex, final int endIndex) {
        return new CharArraySegment(startIndex, endIndex - startIndex, data);
    }

    @Override
    public char[] getArray(int startIndex, int endIndex) {
        final int length = endIndex - startIndex;
        char[] array = new char[length];
        System.arraycopy(data, startIndex, array, 0, length);
        return array;
    }

    @Override
    public BigDecimal getBigDecimal(int startIndex, int endIndex) {
        return new BigDecimal(data, startIndex, endIndex - startIndex);
    }

    @Override
    public BigInteger getBigInteger(int startIndex, int endIndex) {
        final int len = endIndex - startIndex;
        if (len > MAX_LONG_STR_LENGTH) {
            return getBigDecimal(startIndex, endIndex).toBigInteger();
        } else {
            long value = getLong(startIndex, endIndex);
            return BigInteger.valueOf(value);
        }
    }

    @Override
    public String getEncodedString(int start, int end) {
        return CharArrayUtils.decodeJsonString(data, start, end);
    }

    @Override
    public String toEncodedStringIfNeeded(int start, int end) {
        if (CharArrayUtils.hasEscapeChar(data, start, end)) {
            return getEncodedString(start, end);
        } else {
            return this.getString(start, end);
        }
    }

    @Override
    public String toString() {
        return new String(data);
    }

    @Override
    public NumberParseResult findEndOfNumberFast() {


        int i = index + 1;
        char ch = 0;
        final char[] data = this.data;
        final int length = data.length;
        for (; i < length; i++) {

            ch = data[i];

            switch (ch) {

                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                case ATTRIBUTE_SEP:
                case ARRAY_SEP:
                case OBJECT_END_TOKEN:
                case ARRAY_END_TOKEN:
                    index = i;
                    return new NumberParseResult(i, false);

                case NUM_0:
                case NUM_1:
                case NUM_2:
                case NUM_3:
                case NUM_4:
                case NUM_5:
                case NUM_6:
                case NUM_7:
                case NUM_8:
                case NUM_9:
                    break;

                case DECIMAL_POINT:
                    index = i;
                    return findEndOfFloatFast();


                case EXPONENT_MARKER:
                case EXPONENT_MARKER2:
                    index = i;
                    return parseFloatWithExponentFast();


                default:
                    throw new IllegalStateException("Unexpected character " + ch + " at index " + index);

            }

        }

        index = i;
        return new NumberParseResult(i, false);

    }

    private NumberParseResult findEndOfFloatFast() {


        int i = index + 1;
        char ch = 0;
        final char[] data = this.data;
        final int length = data.length;

        for (; i < length; i++) {
            ch = data[i];
            switch (ch) {

                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                case ATTRIBUTE_SEP:
                case ARRAY_SEP:
                case OBJECT_END_TOKEN:
                case ARRAY_END_TOKEN:
                    index = i;
                    return new NumberParseResult(i, true);

                case NUM_0:
                case NUM_1:
                case NUM_2:
                case NUM_3:
                case NUM_4:
                case NUM_5:
                case NUM_6:
                case NUM_7:
                case NUM_8:
                case NUM_9:
                    break;

                case EXPONENT_MARKER:
                case EXPONENT_MARKER2:
                    index = i;
                    return parseFloatWithExponentFast();


                default:
                    throw new UnexpectedCharacterException("Parsing JSON Float Number", "Unexpected character", this, ch, i);

            }

        }


        index = i;
        return new NumberParseResult(i, true);

    }

    private NumberParseResult parseFloatWithExponentFast() {

        int i = index + 1;
        char ch = 0;
        int signOperator = 0;
        final char[] data = this.data;
        final int length = data.length;
        for (; i < length; i++) {
            ch = data[i];

            switch (ch) {

                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                case ATTRIBUTE_SEP:
                case ARRAY_SEP:
                case OBJECT_END_TOKEN:
                case ARRAY_END_TOKEN:
                    index = i;
                    return new NumberParseResult(i, true);

                case MINUS:
                case PLUS:
                    signOperator++;
                    if (signOperator > 1) {
                        throw new IllegalStateException("Too many sign operators when parsing exponent of float");
                    }
                    break;

                case NUM_0:
                case NUM_1:
                case NUM_2:
                case NUM_3:
                case NUM_4:
                case NUM_5:
                case NUM_6:
                case NUM_7:
                case NUM_8:
                case NUM_9:
                    break;


                default:
                    throw new IllegalStateException("Unexpected character " + ch + " at index " + index);

            }

        }


        index = i;
        return new NumberParseResult(i, true);

    }

    @Override
    public int findEndOfEncodedStringFast() {
        int i = ++index;
        final char[] data = this.data;
        final int length = data.length;
        boolean controlChar = false;
        for (; i < length; i++) {
            char ch = data[i];
            switch (ch) {
                case CONTROL_ESCAPE_TOKEN:
                    controlChar = !controlChar;
                    continue;
                case STRING_END_TOKEN:
                    if (!controlChar) {
                        index = i + 1;
                        return i;
                    }
                    controlChar = false;
                    break;
                default:
                    controlChar = false;
                    break;

            }
        }
        throw new IllegalStateException("Unable to find closing for String");
    }

    private int findEndOfStringControlEncode(int i) {
        final char[] data = this.data;
        final int length = data.length;
        char ch = 0;


        ch = data[i];
        switch (ch) {
            case CONTROL_ESCAPE_TOKEN:
            case STRING_END_TOKEN:
            case 'n':
            case 'b':
            case '/':
            case 'r':
            case 't':
            case 'f':
                return i;

            case 'u':
                return findEndOfHexEncoding(i);

            default:
                throw new UnexpectedCharacterException("Parsing JSON String", "Unexpected character while finding closing for String", this, ch, i);

        }

    }

    @Override
    public int findEndOfEncodedString() {
        int i = ++index;
        final char[] data = this.data;
        final int length = data.length;
        char ch = 0;
        for (; i < length; i++) {
            ch = data[i];
            switch (ch) {
                case CONTROL_ESCAPE_TOKEN:
                    i = findEndOfStringControlEncode(i + 1);
                    continue;
                case STRING_END_TOKEN:
                    index = i + 1;
                    return i;
                default:
                    if (ch >= SPACE_WS) {
                        continue;
                    }
                    throw new UnexpectedCharacterException("Parsing JSON String", "Unexpected character while finding closing for String", this, ch, i);

            }
        }

        throw new UnexpectedCharacterException("Parsing JSON Encoded String", "Unable to find closing for String", this, ch, i);
    }

    private int findEndOfHexEncoding(int index) {
        final char[] data = this.data;
        final int length = data.length;

        if (isHex(data[++index]) && isHex(data[++index]) && isHex(data[++index]) && isHex(data[++index])) {
            return index;
        } else {
            throw new UnexpectedCharacterException("Parsing hex encoding in a string", "Unexpected character", this);
        }

    }

    private boolean isHex(char datum) {
        switch (datum) {
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
            default:
                return false;
        }
    }

    @Override
    public int findAttributeEnd() {
        int index = this.index;
        final char[] data = this.data;
        final int length = this.data.length;

        loop:
        for (; index < length; index++) {
            char ch = data[index];
            switch (ch) {
                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                case ATTRIBUTE_SEP:
                    this.index = index;
                    break loop;
            }
        }

        return index;
    }

    @Override
    public boolean findChar(char c) {
        int index = this.index;
        final char[] data = this.data;
        final int length = this.data.length;

        for (; index < length; index++) {
            if (data[index] == c) {
                this.index = index;
                return true;
            }
        }
        return false;
    }

    @Override
    public int findEndString() {

        int i = ++index;
        final char[] data = this.data;
        final int length = data.length;
        char ch = 0;

        for (; i < length; i++) {
           ch = data[i];
            switch (ch) {
                case STRING_END_TOKEN:
                    index = i;
                    return i;
                default:
                    if (ch >= SPACE_WS) {
                        continue;
                    }
                    throw new UnexpectedCharacterException("Parsing JSON String", "Unexpected character while finding closing for String", this,  ch, i);
            }
        }
        throw new UnexpectedCharacterException("Parsing JSON String", "Unable to find closing for String", this,  ch, i);
    }

    @Override
    public NumberParseResult findEndOfNumber() {

        final char startCh = getCurrentChar();
        final int startIndex = index;
        char ch = startCh;


        int i = index + 1;

        final char[] data = this.data;
        final int length = data.length;

        loop:
        for (; i < length; i++) {

            ch = data[i];

            switch (ch) {

                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                case ATTRIBUTE_SEP:
                case ARRAY_SEP:
                case OBJECT_END_TOKEN:
                case ARRAY_END_TOKEN:
                    break loop;

                case NUM_0:
                case NUM_1:
                case NUM_2:
                case NUM_3:
                case NUM_4:
                case NUM_5:
                case NUM_6:
                case NUM_7:
                case NUM_8:
                case NUM_9:
                    break;

                case DECIMAL_POINT:

                    if (startCh == MINUS) {
                        final int numLenSoFar = i - startIndex;
                        if (numLenSoFar == 1) {
                            throw new UnexpectedCharacterException("Parsing JSON Number", "Unexpected character", this, ch, i);
                        }
                    }
                    index = i;
                    return findEndOfFloat();


                case EXPONENT_MARKER:
                case EXPONENT_MARKER2:
                    index = i;
                    return parseFloatWithExponent();


                default:
                    throw new UnexpectedCharacterException("Parsing JSON Number", "Unexpected character", this, ch, i);

            }

        }

        index = i;
        final int numLength = i - startIndex;

        switch (startCh) {
            case NUM_0:
                if (numLength != 1) {
                    throw new UnexpectedCharacterException("Parsing JSON Int Number",
                            "Int can't start with a 0 ", this, startCh, startIndex);
                }
                break;
            case PLUS:
                throw new UnexpectedCharacterException("Parsing JSON Int Number",
                        "Int can't start with a plus ", this, startCh, startIndex);

            case MINUS:
                switch (numLength) {
                    case 1:
                        throw new UnexpectedCharacterException("Parsing JSON Int Number",
                                "Int can't be only a minus, number is missing", this, startCh, startIndex);
                    case 2:
                        break;
                    default:
                        if (data[startIndex + 1] == NUM_0) {

                            throw new UnexpectedCharacterException("Parsing JSON Int Number",
                                    "0 can't be after minus sign", this, startCh, startIndex);
                        }
                }
        }
        return new NumberParseResult(i, false);
    }

    private NumberParseResult findEndOfFloat() {

        int i = index + 1;
        char ch = (char) next();

        if (!isNumber(ch)) {
            throw new UnexpectedCharacterException("Parsing float part of number", "After decimal point expecting number but got", this, ch, this.index);
        }
        final char[] data = this.data;
        final int length = data.length;

        for (; i < length; i++) {
            ch = data[i];
            switch (ch) {

                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                case ATTRIBUTE_SEP:
                case ARRAY_SEP:
                case OBJECT_END_TOKEN:
                case ARRAY_END_TOKEN:
                    index = i;
                    return new NumberParseResult(i, true);

                case NUM_0:
                case NUM_1:
                case NUM_2:
                case NUM_3:
                case NUM_4:
                case NUM_5:
                case NUM_6:
                case NUM_7:
                case NUM_8:
                case NUM_9:
                    break;

                case EXPONENT_MARKER:
                case EXPONENT_MARKER2:
                    index = i;
                    return parseFloatWithExponent();


                default:
                    throw new UnexpectedCharacterException("Parsing JSON Float Number", "Unexpected character", this, ch, i);

            }

        }


        index = i;
        return new NumberParseResult(i, true);

    }

    private boolean isNumber(final char ch) {
        switch (ch) {
            case NUM_0:
            case NUM_1:
            case NUM_2:
            case NUM_3:
            case NUM_4:
            case NUM_5:
            case NUM_6:
            case NUM_7:
            case NUM_8:
            case NUM_9:
                return true;
            default:
                return false;
        }
    }

    private NumberParseResult parseFloatWithExponent() {
        char ch = (char) next();
        if (!isNumberOrSign(ch)) {
            throw new UnexpectedCharacterException("Parsing exponent part of float", "After exponent expecting number or sign but got", this, ch, this.index);
        }

        if (isSign(ch)) {
            ch = (char) next();
            if (!isNumber(ch)) {
                throw new UnexpectedCharacterException("Parsing exponent part of float after sign", "After sign expecting number but got", this, ch, this.index);
            }
        }

        int i = index + 1;
        final char[] data = this.data;
        final int length = data.length;

        for (; i < length; i++) {
            ch = data[i];

            switch (ch) {

                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                case ATTRIBUTE_SEP:
                case ARRAY_SEP:
                case OBJECT_END_TOKEN:
                case ARRAY_END_TOKEN:
                    index = i;
                    return new NumberParseResult(i, true);

                case NUM_0:
                case NUM_1:
                case NUM_2:
                case NUM_3:
                case NUM_4:
                case NUM_5:
                case NUM_6:
                case NUM_7:
                case NUM_8:
                case NUM_9:
                    break;

                default:
                    throw new UnexpectedCharacterException("Parsing Float with exponent", "Unable to find closing for Number", this, ch, i);

            }
        }
        index = i;
        return new NumberParseResult(i, true);
    }

    private boolean isNumberOrSign(char ch) {
        switch (ch) {
            case NUM_0:
            case NUM_1:
            case NUM_2:
            case NUM_3:
            case NUM_4:
            case NUM_5:
            case NUM_6:
            case NUM_7:
            case NUM_8:
            case NUM_9:
            case MINUS:
            case PLUS:
                return true;
            default:
                return false;
        }
    }

    private boolean isSign(char ch) {
        switch (ch) {
            case MINUS:
            case PLUS:
                return true;
            default:
                return false;
        }
    }

    @Override
    public int findFalseEnd() {

        if (this.data[++index] == 'a' && this.data[++index] == 'l' && this.data[++index] == 's' && this.data[++index] == 'e') {
            return ++index;
        } else {
            throw new UnexpectedCharacterException("Parsing JSON False Boolean", "Unexpected character", this);

        }
    }

    @Override
    public int findTrueEnd() {
        if (this.data[++index] == 'r' && this.data[++index] == 'u' && this.data[++index] == 'e') {
            return ++index;
        } else {

            throw new UnexpectedCharacterException("Parsing JSON True Boolean", "Unexpected character", this);
        }
    }

    @Override
    public boolean findObjectEndOrAttributeSep() {
        int i = index;
        char ch = 0;
        final char[] data = this.data;
        final int length = data.length;

        for (; i < length; i++) {
            ch = data[i];
            switch (ch) {
                case OBJECT_END_TOKEN:
                    this.index = i + 1;
                    return true;
                case ATTRIBUTE_SEP:
                    this.index = i;
                    return false;
            }
        }


        throw new UnexpectedCharacterException("Parsing Object Key", "Finding object end or separator", this);
    }

    @Override
    public boolean findCommaOrEndForArray() {
        int i = index;
        char ch = 0;
        final char[] data = this.data;
        final int length = data.length;

        for (; i < length; i++) {
            ch = data[i];
            switch (ch) {
                case ARRAY_END_TOKEN:
                    this.index = i + 1;
                    return true;
                case ARRAY_SEP:
                    this.index = i;
                    return false;

                case NEW_LINE_WS:
                case CARRIAGE_RETURN_WS:
                case TAB_WS:
                case SPACE_WS:
                    continue;

                default:
                    throw new UnexpectedCharacterException("Parsing Object Key", "Finding object end or separator", this, ch, i);
            }
        }


        throw new UnexpectedCharacterException("Parsing Array", "Finding list end or separator", this);
    }

    @Override
    public int findNullEnd() {
        if (this.data[++index] == 'u' && this.data[++index] == 'l' && this.data[++index] == 'l') {
            return ++index;
        } else {
            throw new UnexpectedCharacterException("Parsing JSON Null", "Unexpected character", this);
        }
    }

    @Override
    public boolean matchChars(final int startIndex, final int endIndex, CharSequence key) {

        final int length = endIndex - startIndex;
        int idx = startIndex;

        switch (length) {
            case 1:
                return key.charAt(0) == data[idx];
            case 2:
                return key.charAt(0) == data[idx] &&
                        key.charAt(1) == data[idx + 1];
            case 3:
                return key.charAt(0) == data[idx] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(2) == data[idx + 2];
            case 4:
                return key.charAt(0) == data[idx] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(3) == data[idx + 3];

            case 5:
                return key.charAt(1) == data[idx + 1] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(0) == data[idx] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(4) == data[idx + 4];

            case 6:
                return key.charAt(0) == data[idx] &&
                        key.charAt(5) == data[idx + 5] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(4) == data[idx + 4];

            case 7:
                return key.charAt(0) == data[idx] &&
                        key.charAt(6) == data[idx + 6] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(5) == data[idx + 5] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(4) == data[idx + 4];

            case 8:
                return key.charAt(0) == data[idx] &&
                        key.charAt(7) == data[idx + 7] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(5) == data[idx + 5] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(6) == data[idx + 6] &&
                        key.charAt(4) == data[idx + 4];


            case 9:
                return key.charAt(0) == data[idx] &&
                        key.charAt(8) == data[idx + 8] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(6) == data[idx + 6] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(7) == data[idx + 7] &&
                        key.charAt(4) == data[idx + 4] &&
                        key.charAt(5) == data[idx + 5] &&
                        key.charAt(1) == data[idx + 1];

            case 10:
                return key.charAt(0) == data[idx] &&
                        key.charAt(9) == data[idx + 9] &&
                        key.charAt(6) == data[idx + 6] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(7) == data[idx + 7] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(4) == data[idx + 4] &&
                        key.charAt(5) == data[idx + 5] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(8) == data[idx + 8];

            case 11:
                return key.charAt(0) == data[idx] &&
                        key.charAt(10) == data[idx + 10] &&
                        key.charAt(6) == data[idx + 6] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(7) == data[idx + 7] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(9) == data[idx + 9] &&
                        key.charAt(4) == data[idx + 4] &&
                        key.charAt(5) == data[idx + 5] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(8) == data[idx + 8];

            case 12:
                return key.charAt(0) == data[idx] &&
                        key.charAt(11) == data[idx + 11] &&
                        key.charAt(3) == data[idx + 3] &&
                        key.charAt(7) == data[idx + 7] &&
                        key.charAt(2) == data[idx + 2] &&
                        key.charAt(6) == data[idx + 6] &&
                        key.charAt(9) == data[idx + 9] &&
                        key.charAt(4) == data[idx + 4] &&
                        key.charAt(5) == data[idx + 5] &&
                        key.charAt(10) == data[idx + 10] &&
                        key.charAt(1) == data[idx + 1] &&
                        key.charAt(8) == data[idx + 8];

            default:
                final int start = 0;
                final int end = length - 1;
                final int middle = length / 2;

                if (key.charAt(start) == data[idx] &&
                        key.charAt(end) == data[idx + end] &&
                        key.charAt(middle) == data[idx + middle]) {
                    for (int i = 1; i < length; i++) {
                        if (key.charAt(i) != data[idx + i]) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    return false;
                }
        }

    }

    public boolean isInteger(int offset, int end) {
        int len = end - offset;
        final char[] digitChars = data;
        final boolean negative = (digitChars[offset] == '-');
        final int cmpLen = negative ? MIN_INT_STR_LENGTH : MAX_INT_STR_LENGTH;
        if (len < cmpLen) return true;
        if (len > cmpLen) return false;
        final char[] cmpStr = negative ? MIN_INT_CHARS : MAX_INT_CHARS;
        for (int i = 0; i < cmpLen; ++i) {
            int diff = digitChars[offset + i] - cmpStr[i];
            if (diff != 0) {
                return (diff < 0);
            }
        }
        return true;
    }

    @Override
    public double getDouble(int from, int to) {
        return ParseDouble.parseDouble(data, from, to);
    }

    @Override
    public float getFloat(int from, int to) {
        return ParseFloat.parseFloat(data, from, to);
    }

    @Override
    public int getInt(int offset, int to) {


        final char[] digitChars = data;

        int num;
        boolean negative = false;
        char c = digitChars[offset];
        if (c == '-') {
            offset++;
            negative = true;
        } else if (c == '+') {
            offset++;
            negative = false;
        }

        c = digitChars[offset];
        num = (c - '0');
        offset++;

        int digit;

        for (; offset < to; offset++) {
            c = digitChars[offset];
            digit = (c - '0');
            num = (num * 10) + digit;
        }

        return negative ? num * -1 : num;

    }

    @Override
    public long getLong(int offset, int to) {

        final char[] digitChars = data;

        long num;
        boolean negative = false;
        char c = digitChars[offset];
        if (c == '-') {
            offset++;
            negative = true;
        }

        c = digitChars[offset];
        num = (c - '0');
        offset++;

        long digit;

        for (; offset < to; offset++) {
            c = digitChars[offset];
            digit = (c - '0');
            num = (num * 10) + digit;
        }

        return negative ? num * -1 : num;

    }

    @Override
    public String errorDetails(String message, int index, int ch) {
        StringBuilder buf = new StringBuilder(255);

        final char[] array = data;

        buf.append(message).append("\n");


        buf.append("\n");
        buf.append("The current character read is " + debugCharDescription(ch)).append('\n');


        int line = 0;
        int lastLineIndex = 0;

        for (int i = 0; i < index && i < array.length; i++) {
            if (array[i] == '\n') {
                line++;
                lastLineIndex = i + 1;
            }
        }

        int count = 0;

        for (int i = lastLineIndex; i < array.length; i++, count++) {
            if (array[i] == '\n') {
                break;
            }
        }


        buf.append("line number " + (line + 1)).append('\n');
        buf.append("index number " + index).append('\n');


        try {
            buf.append(new String(array, lastLineIndex, count)).append('\n');
        } catch (Exception ex) {

            try {
                int start = index = (index - 10 < 0) ? 0 : index - 10;

                buf.append(new String(array, start, index)).append('\n');
            } catch (Exception ex2) {
                buf.append(new String(array)).append('\n');
            }
        }
        for (int i = 0; i < (index - lastLineIndex); i++) {
            buf.append('.');
        }
        buf.append('^');

        return buf.toString();
    }
}

```

###### support/

####### ParseFloat.java

```java
package io.nats.jparse.source.support;

/**
 * Parse Float class.
 */
public class ParseFloat {

    private ParseFloat(){}

    static final float[] powersOf10 = {1e0f, 1e1f, 1e2f, 1e3f, 1e4f, 1e5f, 1e6f, 1e7f,
            1e8f, 1e9f, 1e10f, 1e11f, 1e12f, 1e13f, 1e14f, 1e15f, 1e16f, 1e17f, 1e18f};


    /**
     * Parse a float.
     * @param chars chars
     * @param startIndex start index
     * @param endIndex end index
     * @return float
     */
    public static float parseFloat(char[] chars, int startIndex, int endIndex) {
        boolean negative = false;
        int i = startIndex;
        float result = 0;

        // Check for a negative sign
        if (chars[i] == '-') {
            negative = true;
            i++;
        }

        loop:
        while (i < endIndex) {
            char ch = chars[i];
            switch (ch) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    result = result * 10 + (ch - '0');
                    i++;
                    break;
                case '.':
                    result = parseFractionPart(i + 1, endIndex, chars, result);
                    break loop;
                case 'e':
                    result = parseExponent(i + 1, endIndex, chars, result);
                    break loop;
                default:
                    throw new UnexpectedCharacterException("parsing float", "Illegal character", ch, i);
            }
        }


        if (negative) {
            result = -result;
        }

        return result;
    }

    private static float parseFractionPart(int i, int endIndex, char[] chars, float result) {
        float fraction = 0.1f;
        while (i < endIndex) {
            char ch = chars[i];
            switch (ch) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    result += (ch - '0') * fraction;
                    fraction /= 10;
                    i++;
                    break;

                case 'e':
                    return parseExponent(i + 1, endIndex, chars, result);

                default:
                    throw new UnexpectedCharacterException("float parsing fraction part", "Illegal character", ch, i);
            }

        }
        return result;
    }

    private static float parseExponent(int i, int endIndex, char[] chars, float result) {

        boolean exponentNegative = false;
        int exponent = 0;

        char sign = chars[i];

        switch (sign) {
            case '-':
                exponentNegative = true;
                i++;
                break;
            case '+':
                i++;
                break;
        }

        while (i < endIndex) {
            char ch = chars[i];
            switch (chars[i]) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    exponent = exponent * 10 + (ch - '0');
                    i++;
                    break;

                default:
                    throw new UnexpectedCharacterException("float parsing exponent part", "Illegal character", ch, i);

            }
        }


        if (exponentNegative) {
            exponent = -exponent;
        }

        // Use Lookup table for powers of 10

        // Calculate the power of 10
        if (!exponentNegative) {
            while (exponent >= powersOf10.length) {
                result *= 1e18f;
                exponent -= 18;
            }
            result *= powersOf10[exponent];
        } else {
            while (-exponent >= powersOf10.length) {
                result /= 1e18f;
                exponent += 18;
            }
            result /= powersOf10[-exponent];
        }

        return result;
    }
}

```

####### UnexpectedCharacterException.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.source.support;

import io.nats.jparse.source.CharSource;

import java.util.Optional;


/**
 * Exception thrown when an unexpected character is encountered while parsing a JSON string or CharSource.
 *
 * @see CharSource
 * @see io.nats.jparse.parser.JsonParser
 */
/**
 * This class represents an exception that is thrown when an unexpected character is encountered during parsing.
 */
public class UnexpectedCharacterException extends RuntimeException {

    /**
     * The source of characters where the unexpected character was found.
     */
    private final CharSource source;

    /**
     * The operation or context during which the unexpected character was encountered.
     */
    private final String whileDoing;

    /**
     * A detailed message about the exception.
     */
    private final String message;

    /**
     * The index at which the unexpected character was found.
     */
    private final int index;

    /**
     * The unexpected character that was encountered.
     */
    private final int ch;

    /**
     * Constructs a new {@code UnexpectedCharacterException} with the given details.
     *
     * @param whileDoing a string describing the context in which the exception occurred
     * @param message    a message describing the error that occurred
     * @param ch         the unexpected character that caused the exception
     * @param index      the index at which the unexpected character occurred
     */
    public UnexpectedCharacterException(String whileDoing, String message, int ch, int index) {
        super(String.format("Unexpected character while %s, Error is '%s, character is %c' at index %d.", whileDoing, message, (char) ch, index));
        this.source = null;
        this.whileDoing = whileDoing;
        this.message = message;
        this.ch = ch;
        this.index = index;

    }

    /**
     * Constructs a new {@code UnexpectedCharacterException} with the given details.
     *
     * @param whileDoing a string describing the context in which the exception occurred
     * @param message    a message describing the error that occurred
     * @param source     the character source being parsed when the exception occurred
     * @param ch         the unexpected character that caused the exception
     * @param index      the index at which the unexpected character occurred
     */
    public UnexpectedCharacterException(String whileDoing, String message, CharSource source, int ch, int index) {
        super(String.format("Unexpected character while %s, Error is '%s'. \n Details \n %s", whileDoing, message, source.errorDetails(message, index, ch)));
        this.source = source;
        this.whileDoing = whileDoing;
        this.message = message;
        this.ch = ch;
        this.index = index;
    }

    /**
     * Constructs a new {@code UnexpectedCharacterException} with the given details.
     *
     * @param whileDoing a string describing the context in which the exception occurred
     * @param message    a message describing the error that occurred
     * @param source     the character source being parsed when the exception occurred
     * @param ch         the unexpected character that caused the exception
     */
    public UnexpectedCharacterException(String whileDoing, String message, CharSource source, int ch) {
        this(whileDoing, message, source, ch, source.getIndex());
    }

    /**
     * Constructs a new {@code UnexpectedCharacterException} with the given details.
     *
     * @param whileDoing a string describing the context in which the exception occurred
     * @param message    a message describing the error that occurred
     * @param source     the character source being parsed when the exception occurred
     */
    public UnexpectedCharacterException(String whileDoing, String message, CharSource source) {
        this(whileDoing, message, source, source.getCurrentCharSafe(), source.getIndex());
    }

    /**
     * Constructs a new {@code UnexpectedCharacterException} with the given details.
     *
     * @param whileDoing                   a string describing the context in which the exception occurred
     * @param unexpectedCharacterException a exception describing the error that occurred
     * @param charSource                   the character source being parsed when the exception occurred
     */
    public UnexpectedCharacterException(String whileDoing, CharSource charSource, UnexpectedCharacterException unexpectedCharacterException) {
        this(whileDoing, unexpectedCharacterException.getLocalizedMessage(), charSource);
    }

    /**
     * The source of the error.
     *
     * @return source
     */
    public Optional<CharSource> source() {
        return Optional.ofNullable(source);
    }

    /**
     * What we were doing when the error occurred.
     *
     * @return what we were doing when the error occurred.
     */
    public String getWhileDoing() {
        return whileDoing;
    }

    /**
     * The message for the error.
     *
     * @return message for the error.
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * The index in the source where the error occurred.
     *
     * @return index in the source where the error occurred.
     */
    public int getIndex() {
        return index;
    }

    /**
     * The character that caused the error.
     *
     * @return character that caused the error.
     */
    public int getCh() {
        return ch;
    }

    /**
     * Details about the error.
     *
     * @return details about the error.
     */
    public String getDetails() {
        return source.errorDetails(message, index, ch);
    }
}

```

####### PathException.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.source.support;

import io.nats.jparse.source.CharSource;

/**
 * Exception class for handling errors related to JSON paths.
 * This is a runtime exception that can be thrown when an unexpected condition
 * is met while processing a path.
 */
public class PathException extends RuntimeException {

    /**
     * Constructs a new PathException with a detailed error message.
     * The error message is formatted to include specific details about the operation,
     * the error, and the location in the source where the error occurred.
     *
     * @param whileDoing The operation being performed when the error occurred.
     * @param message The specific error message.
     * @param source The CharSource where the error occurred.
     * @param index The index in the source where the error occurred.
     */
    public PathException(String whileDoing, String message, CharSource source, int index) {
        super(String.format("Unexpected character while %s, Error is '%s'. \n Details \n %s",
                whileDoing, message, source.errorDetails(message, index, source.getChartAt(index))));
    }
}

```

####### ParseDouble.java

```java
package io.nats.jparse.source.support;

/**
 * Parse Double class.
 */
public class ParseDouble {
    private ParseDouble(){}

    static final double[] powersOf10 = {1e0, 1e1, 1e2, 1e3, 1e4, 1e5, 1e6, 1e7,
            1e8, 1e9, 1e10, 1e11, 1e12, 1e13, 1e14, 1e15, 1e16, 1e17, 1e18, 1e19, 1e20, 1e21, 1e22};


    /**
     * Parse a float.
     * @param chars chars
     * @param startIndex start index
     * @param endIndex end index
     * @return float
     */
    public static double parseDouble(char[] chars, int startIndex, int endIndex) {


        boolean negative = false;
        int i = startIndex;
        double result = 0;

        // Check for a negative sign
        if (chars[i] == '-') {
            negative = true;
            i++;
        }

        loop:
        while (i < endIndex) {
            char ch = chars[i];
            switch (ch) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    result = result * 10 + (ch - '0');
                    i++;
                    break;
                case '.':
                    result = parseFractionPart(i + 1, endIndex, chars, result);
                    break loop;
                case 'E':
                case 'e':
                    result = parseExponent(i + 1, endIndex, chars, result);
                    break loop;
                default:
                    throw new UnexpectedCharacterException("parsing double", "Illegal character", ch, i);
            }
        }


        if (negative) {
            result = -result;
        }

        return result;
    }

    private static double parseFractionPart(int i, int endIndex, char[] chars, double result) {
        double fraction = 0.1;
        while (i < endIndex) {
            char ch = chars[i];
            switch (ch) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    result += (ch - '0') * fraction;
                    fraction /= 10;
                    i++;
                    break;

                case 'E':
                case 'e':
                    return parseExponent(i + 1, endIndex, chars, result);

                default:
                    throw new UnexpectedCharacterException("double parsing fraction part", "Illegal character", ch, i);
            }

        }
        return result;
    }


    private static double parseExponent(int i, int endIndex, char[] chars, double result) {

        boolean exponentNegative = false;
        int exponent = 0;


        char sign = chars[i];

        switch (sign) {
            case '-':
                exponentNegative = true;
                i++;
                break;
            case '+':
                i++;
                break;
        }

        while (i < endIndex) {
            char ch = chars[i];
            switch (chars[i]) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    exponent = exponent * 10 + (ch - '0');
                    i++;
                    break;

                default:
                    throw new UnexpectedCharacterException("double parsing parsing exponent", "Illegal character", ch, i);
            }
        }

        if (exponentNegative) {
            exponent = -exponent;
        }

        // Use Lookup table for powers of 10

        // Calculate the power of 10
        if (!exponentNegative) {
            while (exponent >= powersOf10.length) {
                result *= 1e22;
                exponent -= 22;
            }
            result *= powersOf10[exponent];
        } else {
            while (-exponent >= powersOf10.length) {
                result /= 1e22;
                exponent += 22;
            }
            result /= powersOf10[-exponent];
        }

        return result;
    }

}

```

####### CharArraySegment.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.source.support;

/**
 * A segment of a character array (char[]), providing access to a subsequence
 * of the array as a {@link CharSequence}.
 */
public class CharArraySegment implements CharSequence {

    private final int offset;
    private final int length;
    private final char[] data;

    /**
     * Creates a new CharArraySegment that starts at the given offset in the
     * character array and extends for the specified length.
     *
     * @param offset The starting index in the character array.
     * @param length The number of characters in the segment.
     * @param data   The character array.
     */
    public CharArraySegment(int offset, int length, char[] data) {
        this.offset = offset;
        this.length = length;
        this.data = data;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public char charAt(int index) {
        return data[offset + index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new CharArraySegment(start + offset, end - start, data);
    }

    @Override
    public String toString() {
        return new String(data, offset, length);
    }

    /**
     * Compares this CharArraySegment to another object for equality. The other
     * object is considered equal if it is a CharArraySegment with the same
     * length and same characters, or if it is a CharSequence with the same
     * length and same characters.
     *
     * @param o The object to compare for equality.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof CharArraySegment) {
            CharArraySegment other = (CharArraySegment) o;

            if (other.length != this.length) {
                return false;
            }

            final int end = length + offset;
            for (int i = offset, j = other.offset; i < end; i++, j++) {
                final char cOther = other.data[j];
                final char cThis = data[i];
                if (cOther != cThis) {
                    return false;
                }
            }
            return true;
        } else if (o instanceof CharSequence) {
            CharSequence other = (CharSequence) o;

            if (other.length() != this.length) {
                return false;
            }
            final int end = length + offset;
            for (int i = offset, j = 0; i < end; i++, j++) {
                final char cOther = other.charAt(j);
                final char cThis = data[i];
                if (cOther != cThis) {
                    return false;
                }
            }
            return true;
        }

        return false;

    }

    /**
     * Returns a hash code value for this CharArraySegment.
     *
     * @return A hash code value for this CharArraySegment.
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}

```

##### parser/

###### JsonParser.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.parser;

import io.nats.jparse.node.RootNode;
import io.nats.jparse.node.support.ParseConstants;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.source.Sources;
import io.nats.jparse.token.Token;

import java.util.List;

/**
 * The `JsonParser` interface provides methods for scanning and parsing JSON strings. It defines methods for
 * scanning a character source and returning a list of tokens, as well as for parsing a character source
 * and returning a root node representing the parsed JSON. The interface also includes default methods
 * for parsing and scanning strings,
 * and it extends the `ParseConstants` interface, which defines constants used for parsing JSON strings.
 */
public interface JsonParser extends ParseConstants {

    /**
     * Scan a character source and return a list of tokens representing the JSON string.
     *
     * @param source The character source to scan
     * @return A list of tokens representing the JSON string
     */
    List<Token> scan(final CharSource source);

    /**
     * Parse a character source and return a root node representing the parsed JSON.
     *
     * @param source The character source to parse
     * @return A root node representing the parsed JSON
     */
    RootNode parse(final CharSource source);

    /**
     * Parse a string and return a root node representing the parsed JSON.
     *
     * @param source The string to parse
     * @return A root node representing the parsed JSON
     */
    default RootNode parse(final String source) {
        return parse(Sources.stringSource(source));
    }

    /**
     * Scan a string and return a list of tokens representing the JSON string.
     *
     * @param source The string to scan
     * @return A list of tokens representing the JSON string
     */
    default List<Token> scan(final String source) {
        return scan(Sources.stringSource(source));
    }
}

```

###### JsonParserBuilder.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.parser;


import io.nats.jparse.parser.indexoverlay.JsonFastParser;
import io.nats.jparse.parser.indexoverlay.JsonStrictParser;


/**
 * Builder class for creating instances of `JsonParser`.
 *
 * @see JsonParserBuilder#build() for details on how the build logic is formed.
 */
public class JsonParserBuilder {


    /**
     * Whether to use strict parsing when parsing JSON.
     */
    private boolean strict = false;

    /**
     * Whether object keys can be encoded when parsing JSON.
     */
    private boolean objectsKeysCanBeEncoded;



    /**
     * Builds a new instance of `JsonParser`.
     *
     */
    public JsonParserBuilder() {

    }

    /**
     * Returns a new instance of `JsonParserBuilder`.
     *
     * @return a new instance
     */
    public static JsonParserBuilder builder() {
        return new JsonParserBuilder();
    }



    /**
     * Gets whether to use strict parsing when parsing JSON.
     *
     * @return `true` if strict parsing is used, `false` otherwise
     */
    public boolean strict() {
        return strict;
    }

    /**
     * Sets whether to use strict parsing when parsing JSON.
     *
     * @param strict `true` to use strict parsing, `false` otherwise
     * @return the modified builder
     */
    public JsonParserBuilder setStrict(boolean strict) {
        this.strict = strict;
        return this;
    }

    /**
     * Gets whether object keys can be encoded when parsing JSON.
     *
     * @return `true` if object keys can be encoded, `false` otherwise
     */
    public boolean objectsKeysCanBeEncoded() {
        return objectsKeysCanBeEncoded;
    }

    /**
     * Sets whether object keys can be encoded when parsing JSON.
     *
     * @param objectsKeysCanBeEncoded `true` to allow encoded keys, `false` otherwise
     * @return the modified builder
     */
    public JsonParserBuilder setObjectsKeysCanBeEncoded(boolean objectsKeysCanBeEncoded) {
        this.objectsKeysCanBeEncoded = objectsKeysCanBeEncoded;
        return this;
    }


    /**
     * Returns a new instance of `JsonParser`.
     * <p>
     * The `JsonParserBuilder.build()` function returns an instance of `JsonParser`.
     * If any of the following conditions are met: `supportNoQuoteKeys` is `true`,
     * `allowHashComment` is `true`, `allowSlashSlashComment` is `true`,
     * `allowSlashStarComment` is `true`, or `parseKey` is not `null`,
     * the function sets up a parse function table for the JSON parser. It then
     * returns a new instance of `JsonParser`.
     * <p>
     * The function first checks if any of the conditions for setting up a parse
     * function table are true. If so, it sets up an array of `ParseFunction`
     * objects and populates it with parsing functions based on the type of JSON
     * element being parsed.
     * <p>
     * If `isAllowHashComment()` is true, the function sets the hash character `#`
     * to a function that skips the comment until the end of the line.
     * <p>
     * If `isSupportNoQuoteKeys()` is true and `getParseKey()` is null, the function
     * sets the parse function for keys to `JsonParserFunctions::parseKeyNoQuote`.
     * <p>
     * If `isAllowSlashStarComment()` or `isAllowSlashSlashComment()` is true, the
     * function sets the forward slash `/` character to a function that handles
     * comments. If the next character is another forward slash, the function skips
     * to the end of the line. If the next character is an asterisk, it skips
     * everything until it reaches a closing asterisk-slash sequence.
     * <p>
     * After setting up the parse function table, the function returns a new
     * instance of `JsonFuncParser` with the table, default parse function, and
     * parse function for keys.
     * <p>
     * If none of the above conditions are met but `strict()` is true, the function
     * returns a new instance of `JsonStrictParser`. If `strict()` is false, the
     * function returns a new instance of `JsonFastParser`.
     * <p>
     * The `JsonParserBuilder.build()` function checks if any of the conditions for
     * setting up a parse function table are true. If so, it sets up an array of
     * `ParseFunction` objects and populates it with parsing functions. If none of
     * those conditions are true but `strict()` is true, it returns a new instance
     * of `JsonStrictParser`. If `strict()` is false, it returns a new instance of
     * `JsonFastParser`.
     * <p>
     * The `ParseFunction` array is populated with parsing functions based on the
     * type of JSON element being parsed. For example, the string start token is
     * assigned the `JsonParserFunctions::parseString` function, while the null
     * start token is assigned the `JsonParserFunctions::parseNull` function.
     * The `JsonParserFunctions::parseNumber` function is assigned to all numbers,
     * and the minus and plus signs.
     * <p>
     * If `isAllowHashComment()` is true, the function sets the hash character `#`
     * to a function that skips the comment until the end of the line.
     * <p>
     * If `isSupportNoQuoteKeys()` is true and `getParseKey()` is null, the function
     * sets the parse function for keys to `JsonParserFunctions::parseKeyNoQuote`.
     * <p>
     * If `isAllowSlashStarComment()` or `isAllowSlashSlashComment()` is true, the
     * function sets the forward slash `/` character to a function that handles
     * comments. If the next character is another forward slash, the function skips
     * to the end of the line. If the next character is an asterisk, it skips
     * everything until it reaches a closing asterisk-slash  sequence.
     * <p>
     * After setting up the parse function table, the function returns a new
     * instance of `JsonFuncParser` with the parse function table, default parse function, and
     * parse function for keys.
     *
     * @return a new instance of `JsonParser`
     */
    public JsonParser build() {

        if (strict()) {
            return new JsonStrictParser(objectsKeysCanBeEncoded());
        } else {
            return new JsonFastParser(objectsKeysCanBeEncoded());
        }
    }

}

```

###### indexoverlay/

####### JsonStrictParser.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.parser.indexoverlay;

import io.nats.jparse.node.RootNode;
import io.nats.jparse.node.support.NumberParseResult;
import io.nats.jparse.node.support.TokenList;
import io.nats.jparse.parser.JsonParser;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.source.support.UnexpectedCharacterException;
import io.nats.jparse.token.Token;
import io.nats.jparse.token.TokenTypes;

import java.util.List;

/**
 * The `JsonStrictParser` class is an implementation of the `JsonParser` interface that uses a strict
 * JSON parsing algorithm. The parser does not accept JSON strings that are not strictly compliant
 * with the JSON RFC.
 */
public class JsonStrictParser implements JsonParser {

    private final boolean objectsKeysCanBeEncoded;
    int nestLevel;


    /**
     * Create a new `JsonStrictParser` instance.
     *
     * @param objectsKeysCanBeEncoded If `true`, then object keys can be encoded (e.g. `{"key\n\t": "value"}`).
     */
    public JsonStrictParser(boolean objectsKeysCanBeEncoded) {
        this.objectsKeysCanBeEncoded = objectsKeysCanBeEncoded;
    }


    /**
     * Scan a character source and return a list of tokens representing the JSON string.
     *
     * @param source The character source to scan
     * @return A list of tokens representing the JSON
     */
    @Override
    public List<Token> scan(final CharSource source) {
        return scan(source, new TokenList());
    }

    /**
     * Parse a character source and return a root node representing the parsed JSON.
     *
     * @param source The character source to parse
     * @return A root node representing the parsed JSON
     */
    @Override
    public RootNode parse(CharSource source) {

        return new RootNode((TokenList) scan(source), source, objectsKeysCanBeEncoded);
    }

    private List<Token> scan(final CharSource source, TokenList tokens) {
        nestLevel = 0;

        int ch = source.nextSkipWhiteSpace();

        switch (ch) {
            case OBJECT_START_TOKEN:
                parseObject(source, tokens);
                break;

            case ARRAY_START_TOKEN:
                parseArray(source, tokens);
                break;

            case TRUE_BOOLEAN_START:
                parseTrue(source, tokens);
                break;

            case FALSE_BOOLEAN_START:
                parseFalse(source, tokens);
                break;

            case NULL_START:
                parseNull(source, tokens);
                break;

            case STRING_START_TOKEN:
                parseString(source, tokens);
                break;

            case NUM_0:
            case NUM_1:
            case NUM_2:
            case NUM_3:
            case NUM_4:
            case NUM_5:
            case NUM_6:
            case NUM_7:
            case NUM_8:
            case NUM_9:
            case MINUS:
            case PLUS:
                parseNumber(source, tokens);
                break;

            default:
                throw new UnexpectedCharacterException("Scanning JSON", "Unexpected character", source, (char) ch);

        }

        source.checkForJunk();

        return tokens;
    }

    private void parseFalse(CharSource source, TokenList tokens) {
        int start = source.getIndex();
        int end = source.findFalseEnd();
        tokens.add(new Token(start, end, TokenTypes.BOOLEAN_TOKEN));
    }

    private void parseTrue(CharSource source, TokenList tokens) {
        int start = source.getIndex();
        int end = source.findTrueEnd();
        tokens.add(new Token(start, end, TokenTypes.BOOLEAN_TOKEN));
    }

    private void parseNull(CharSource source, TokenList tokens) {
        int start = source.getIndex();
        int end = source.findNullEnd();
        tokens.add(new Token(start, end, TokenTypes.NULL_TOKEN));
    }

    private void parseArray(final CharSource source, final TokenList tokens) {
        levelCheck(source);
        final int startSourceIndex = source.getIndex();
        final int tokenListIndex = tokens.getIndex();
        tokens.placeHolder();

        boolean done = false;
        while (!done) {
            done = parseArrayItem(source, tokens);

            if (!done) {
                done = source.findCommaOrEndForArray();
            }
        }

        final Token arrayToken = new Token(startSourceIndex, source.getIndex(), TokenTypes.ARRAY_TOKEN);
        tokens.set(tokenListIndex, arrayToken);
    }


    private boolean parseArrayItem(CharSource source, TokenList tokens) {
        char startChar = source.getCurrentChar();
        int ch = source.nextSkipWhiteSpace();

        switch (ch) {
            case OBJECT_START_TOKEN:
                parseObject(source, tokens);
                break;

            case ARRAY_START_TOKEN:
                parseArray(source, tokens);
                break;

            case TRUE_BOOLEAN_START:
                parseTrue(source, tokens);
                break;

            case FALSE_BOOLEAN_START:
                parseFalse(source, tokens);
                break;


            case NULL_START:
                parseNull(source, tokens);
                break;

            case STRING_START_TOKEN:
                parseString(source, tokens);
                break;

            case NUM_0:
            case NUM_1:
            case NUM_2:
            case NUM_3:
            case NUM_4:
            case NUM_5:
            case NUM_6:
            case NUM_7:
            case NUM_8:
            case NUM_9:
            case MINUS:
            case PLUS:
                parseNumber(source, tokens);
                if (source.getCurrentChar() == ARRAY_END_TOKEN || source.getCurrentChar() == ARRAY_SEP) {
                    if (source.getCurrentChar() == ARRAY_END_TOKEN) {
                        source.next();
                        return true;
                    }
                }
                break;

            case ARRAY_END_TOKEN:
                if (startChar == ARRAY_SEP) {
                    throw new UnexpectedCharacterException("Parsing Array Item", "Trailing comma", source, (char) ch);
                }
                source.next();
                return true;


            default:
                throw new UnexpectedCharacterException("Parsing Array Item", "Unexpected character", source, (char) ch);

        }

        return false;
    }

    private void parseNumber(final CharSource source, TokenList tokens) {
        final int startIndex = source.getIndex();
        final NumberParseResult numberParse = source.findEndOfNumber();
        tokens.add(new Token(startIndex, numberParse.endIndex(), numberParse.wasFloat() ? TokenTypes.FLOAT_TOKEN : TokenTypes.INT_TOKEN));
    }


    private boolean parseKey(final CharSource source, final TokenList tokens) {
        final char startChar = source.getCurrentChar();

        int ch = source.nextSkipWhiteSpace();
        final int startIndex = source.getIndex() - 1;
        final int tokenListIndex = tokens.getIndex();
        tokens.placeHolder();
        boolean found = false;

        switch (ch) {

            case STRING_START_TOKEN:
                final int strStartIndex = startIndex + 1;
                final int strEndIndex;
                if (objectsKeysCanBeEncoded) {
                    strEndIndex = source.findEndOfEncodedString();
                } else {
                    strEndIndex = source.findEndString();
                }
                tokens.add(new Token(strStartIndex + 1, strEndIndex, TokenTypes.STRING_TOKEN));
                found = true;
                break;

            case OBJECT_END_TOKEN:

                if (startChar == OBJECT_ATTRIBUTE_SEP) {
                    throw new UnexpectedCharacterException("Parsing key", "Unexpected character found", source);
                }
                tokens.undoPlaceholder();
                return true;

            default:
                throw new UnexpectedCharacterException("Parsing key", "Unexpected character found", source);
        }


        boolean done = source.findObjectEndOrAttributeSep();

        if (!done && found) {
            tokens.set(tokenListIndex, new Token(startIndex + 1, source.getIndex(), TokenTypes.ATTRIBUTE_KEY_TOKEN));
        } else if (found && done) {

            throw new UnexpectedCharacterException("Parsing key", "Not found", source);

        }

        return done;

    }


    private boolean parseValue(final CharSource source, TokenList tokens) {
        int ch = source.nextSkipWhiteSpace();
        final int startIndex = source.getIndex();
        final int tokenListIndex = tokens.getIndex();
        tokens.placeHolder();

        switch (ch) {
            case OBJECT_START_TOKEN:
                parseObject(source, tokens);
                break;

            case ARRAY_START_TOKEN:
                parseArray(source, tokens);
                break;

            case TRUE_BOOLEAN_START:
                parseTrue(source, tokens);
                break;

            case FALSE_BOOLEAN_START:
                parseFalse(source, tokens);
                break;

            case NULL_START:
                parseNull(source, tokens);
                break;

            case STRING_START_TOKEN:
                parseString(source, tokens);
                break;

            case NUM_0:
            case NUM_1:
            case NUM_2:
            case NUM_3:
            case NUM_4:
            case NUM_5:
            case NUM_6:
            case NUM_7:
            case NUM_8:
            case NUM_9:
            case MINUS:
            case PLUS:
                parseNumber(source, tokens);
                break;

            default:
                throw new UnexpectedCharacterException("Parsing Value", "Unexpected character", source, ch);
        }

        source.skipWhiteSpace();

        switch (source.getCurrentChar()) {
            case OBJECT_END_TOKEN:
                if (source.getIndex() == tokenListIndex) {
                    throw new UnexpectedCharacterException("Parsing Value", "Key separator before value", source);
                }
                tokens.set(tokenListIndex, new Token(startIndex, source.getIndex(), TokenTypes.ATTRIBUTE_VALUE_TOKEN));
                return true;
            case OBJECT_ATTRIBUTE_SEP:
                if (source.getIndex() == tokenListIndex) {
                    throw new UnexpectedCharacterException("Parsing Value", "Key separator before value", source);
                }
                tokens.set(tokenListIndex, new Token(startIndex, source.getIndex(), TokenTypes.ATTRIBUTE_VALUE_TOKEN));
                return false;

            default:
                throw new UnexpectedCharacterException("Parsing Value", "Unexpected character", source, source.getCurrentChar());

        }

    }

    private void parseString(final CharSource source, TokenList tokens) {
        final int startIndex = source.getIndex();
        final int endIndex = source.findEndOfEncodedString();
        tokens.add(new Token(startIndex + 1, endIndex, TokenTypes.STRING_TOKEN));
    }


    private void parseObject(final CharSource source, TokenList tokens) {
        levelCheck(source);
        final int startSourceIndex = source.getIndex();
        final int tokenListIndex = tokens.getIndex();
        tokens.placeHolder();

        boolean done = false;
        while (!done) {
            done = parseKey(source, tokens);
            if (!done)
                done = parseValue(source, tokens);
        }
        source.next();
        tokens.set(tokenListIndex, new Token(startSourceIndex, source.getIndex(), TokenTypes.OBJECT_TOKEN));
    }

    private void levelCheck(CharSource source) {
        nestLevel++;
        if (nestLevel > NEST_LEVEL) {
            throw new UnexpectedCharacterException("Next level violation", "Too many levels " + nestLevel, source);
        }
    }


}

```

####### JsonFastParser.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.parser.indexoverlay;

import io.nats.jparse.node.RootNode;
import io.nats.jparse.node.support.NumberParseResult;
import io.nats.jparse.node.support.TokenList;
import io.nats.jparse.parser.JsonParser;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.source.support.UnexpectedCharacterException;
import io.nats.jparse.token.Token;
import io.nats.jparse.token.TokenTypes;

import java.util.List;

/**
 * The `JsonFastParser` class provides methods for scanning and parsing JSON strings. It defines methods for
 * scanning a character source and returning a list of tokens, as well as for parsing a character source
 * and returning a root node representing the parsed JSON. The class also includes default methods
 * for parsing and scanning strings,
 * and it extends the `ParseConstants` interface, which defines constants used for parsing JSON strings.
 */
public class JsonFastParser implements JsonParser {

    private final boolean objectsKeysCanBeEncoded;


    /**
     * Create a new `JsonFastParser` instance.
     *
     * @param objectsKeysCanBeEncoded If `true`, then object keys can be encoded (e.g. `{"key\n\t": "value"}`).
     */
    public JsonFastParser(boolean objectsKeysCanBeEncoded) {
        this.objectsKeysCanBeEncoded = objectsKeysCanBeEncoded;
    }

    /**
     * Scan a character source and return a list of tokens representing the JSON string.
     *
     * @param source The character source to scan
     * @return A list of tokens representing the JSON
     */
    @Override
    public List<Token> scan(final CharSource source) {
        return scan(source, new TokenList());
    }

    /**
     * Parse a character source and return a root node representing the parsed JSON.
     *
     * @param source The character source to parse
     * @return A root node representing the parsed JSON
     */
    @Override
    public RootNode parse(CharSource source) {
        return new RootNode((TokenList) scan(source), source, objectsKeysCanBeEncoded);
    }

    private List<Token> scan(final CharSource source, TokenList tokens) {

        int ch = source.nextSkipWhiteSpace();

        switch (ch) {
            case OBJECT_START_TOKEN:
                parseObject(source, tokens);
                break;

            case ARRAY_START_TOKEN:
                parseArray(source, tokens);
                break;

            case TRUE_BOOLEAN_START:
                parseTrue(source, tokens);
                break;

            case FALSE_BOOLEAN_START:
                parseFalse(source, tokens);
                break;

            case NULL_START:
                parseNull(source, tokens);
                break;

            case STRING_START_TOKEN:
                parseString(source, tokens);
                break;

            case NUM_0:
            case NUM_1:
            case NUM_2:
            case NUM_3:
            case NUM_4:
            case NUM_5:
            case NUM_6:
            case NUM_7:
            case NUM_8:
            case NUM_9:
            case MINUS:
            case PLUS:
                parseNumber(source, tokens);
                break;

            default:
                throw new UnexpectedCharacterException("Scanning JSON", "Unexpected character", source, (char) ch);

        }

        return tokens;
    }

    private void parseFalse(CharSource source, TokenList tokens) {
        int start = source.getIndex();
        int end = source.findFalseEnd();
        tokens.add(new Token(start, end, TokenTypes.BOOLEAN_TOKEN));
    }

    private void parseTrue(CharSource source, TokenList tokens) {
        int start = source.getIndex();
        int end = source.findTrueEnd();
        tokens.add(new Token(start, end, TokenTypes.BOOLEAN_TOKEN));
    }

    private void parseNull(CharSource source, TokenList tokens) {
        int start = source.getIndex();
        int end = source.findNullEnd();
        tokens.add(new Token(start, end, TokenTypes.NULL_TOKEN));
    }

    private void parseArray(final CharSource source, final TokenList tokens) {
        final int startSourceIndex = source.getIndex();
        final int tokenListIndex = tokens.getIndex();
        tokens.placeHolder();

        boolean done = false;
        while (!done) {
            done = parseArrayItem(source, tokens);

        }
        final Token arrayToken = new Token(startSourceIndex, source.getIndex(), TokenTypes.ARRAY_TOKEN);
        tokens.set(tokenListIndex, arrayToken);
    }

    private boolean parseArrayItem(CharSource source, TokenList tokens) {
        char ch = (char) source.nextSkipWhiteSpace();

        forLoop:
        for (; ch != ETX; ch = (char) source.nextSkipWhiteSpace()) {

            switch (ch) {
                case OBJECT_START_TOKEN:
                    parseObject(source, tokens);
                    break forLoop;

                case ARRAY_START_TOKEN:
                    parseArray(source, tokens);
                    break forLoop;

                case TRUE_BOOLEAN_START:
                    parseTrue(source, tokens);
                    break forLoop;

                case FALSE_BOOLEAN_START:
                    parseFalse(source, tokens);
                    break forLoop;


                case NULL_START:
                    parseNull(source, tokens);
                    break forLoop;

                case STRING_START_TOKEN:
                    parseString(source, tokens);
                    break forLoop;

                case NUM_0:
                case NUM_1:
                case NUM_2:
                case NUM_3:
                case NUM_4:
                case NUM_5:
                case NUM_6:
                case NUM_7:
                case NUM_8:
                case NUM_9:
                case MINUS:
                case PLUS:
                    parseNumber(source, tokens);
                    break forLoop;

                case ARRAY_END_TOKEN:
                    source.next();
                    return true;

                case ARRAY_SEP:
                    source.next();
                    return false;

                default:
                    throw new UnexpectedCharacterException("Parsing Array Item", "Unexpected character", source, ch);


            }
        }

        if (source.getCurrentChar() == ARRAY_END_TOKEN) {
            source.next();
            return true;
        }
        return false;
    }


    private void parseNumber(final CharSource source, TokenList tokens) {
        final int startIndex = source.getIndex();
        final NumberParseResult numberParse = source.findEndOfNumberFast();
        tokens.add(new Token(startIndex, numberParse.endIndex(), numberParse.wasFloat() ? TokenTypes.FLOAT_TOKEN : TokenTypes.INT_TOKEN));
    }


    private boolean parseKey(final CharSource source, final TokenList tokens) {

        int ch = source.nextSkipWhiteSpace();
        final int startIndex = source.getIndex() - 1;
        final int tokenListIndex = tokens.getIndex();
        tokens.placeHolder();
        boolean found = false;

        switch (ch) {

            case STRING_START_TOKEN:
                final int strStartIndex = startIndex + 1;
                final int strEndIndex;
                if (objectsKeysCanBeEncoded) {
                    strEndIndex = source.findEndOfEncodedString();
                } else {
                    strEndIndex = source.findEndString();
                }
                tokens.add(new Token(strStartIndex + 1, strEndIndex, TokenTypes.STRING_TOKEN));
                found = true;
                break;

            case OBJECT_END_TOKEN:
                tokens.undoPlaceholder();
                return true;

            default:
                throw new UnexpectedCharacterException("Parsing key", "Unexpected character found", source);
        }


        boolean done = source.findObjectEndOrAttributeSep();

        if (!done && found) {
            tokens.set(tokenListIndex, new Token(startIndex + 1, source.getIndex(), TokenTypes.ATTRIBUTE_KEY_TOKEN));
        } else if (found && done) {

            throw new UnexpectedCharacterException("Parsing key", "Not found", source);

        }

        return done;

    }


    private boolean parseValue(final CharSource source, TokenList tokens) {
        int ch = source.nextSkipWhiteSpace();
        final int startIndex = source.getIndex();
        final int tokenListIndex = tokens.getIndex();
        tokens.placeHolder();

        switch (ch) {
            case OBJECT_START_TOKEN:
                parseObject(source, tokens);
                break;

            case ARRAY_START_TOKEN:
                parseArray(source, tokens);
                break;

            case TRUE_BOOLEAN_START:
                parseTrue(source, tokens);
                break;

            case FALSE_BOOLEAN_START:
                parseFalse(source, tokens);
                break;

            case NULL_START:
                parseNull(source, tokens);
                break;

            case STRING_START_TOKEN:
                parseString(source, tokens);
                break;

            case NUM_0:
            case NUM_1:
            case NUM_2:
            case NUM_3:
            case NUM_4:
            case NUM_5:
            case NUM_6:
            case NUM_7:
            case NUM_8:
            case NUM_9:
            case MINUS:
            case PLUS:
                parseNumber(source, tokens);
                break;

            default:
                throw new UnexpectedCharacterException("Parsing Value", "Unexpected character", source, ch);
        }

        ch = source.skipWhiteSpace();

        switch (ch) {
            case OBJECT_END_TOKEN:
                if (source.getIndex() == tokenListIndex) {
                    throw new UnexpectedCharacterException("Parsing Value", "Key separator before value", source);
                }
                tokens.set(tokenListIndex, new Token(startIndex, source.getIndex(), TokenTypes.ATTRIBUTE_VALUE_TOKEN));
                return true;
            case OBJECT_ATTRIBUTE_SEP:
                if (source.getIndex() == tokenListIndex) {
                    throw new UnexpectedCharacterException("Parsing Value", "Key separator before value", source);
                }
                tokens.set(tokenListIndex, new Token(startIndex, source.getIndex(), TokenTypes.ATTRIBUTE_VALUE_TOKEN));
                return false;

            default:
                throw new UnexpectedCharacterException("Parsing Value", "Unexpected character", source, source.getCurrentChar());

        }

    }

    private void parseString(final CharSource source, TokenList tokens) {
        final int startIndex = source.getIndex();
        final int endIndex = source.findEndOfEncodedStringFast();
        tokens.add(new Token(startIndex + 1, endIndex, TokenTypes.STRING_TOKEN));
    }


    private void parseObject(final CharSource source, TokenList tokens) {
        final int startSourceIndex = source.getIndex();
        final int tokenListIndex = tokens.getIndex();
        tokens.placeHolder();

        boolean done = false;
        while (!done) {
            done = parseKey(source, tokens);
            if (!done)
                done = parseValue(source, tokens);
        }
        source.next();
        tokens.set(tokenListIndex, new Token(startSourceIndex, source.getIndex(), TokenTypes.OBJECT_TOKEN));
    }


}

```

##### node/

###### NullNode.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node;

import io.nats.jparse.source.CharSource;
import io.nats.jparse.token.Token;

import java.util.Collections;
import java.util.List;

/**
 * The NullNode class represents a null value node in a tree structure.
 * <p>
 * It implements the ScalarNode interface.
 */
public class NullNode implements ScalarNode {

    private final Token token;
    private final CharSource source;

    /**
     * Constructs a NullNode with the specified token and source.
     *
     * @param token  the token representing the null value
     * @param source the character source containing the null value
     */
    public NullNode(final Token token, final CharSource source) {
        this.token = token;
        this.source = source;
    }

    /**
     * Returns the element type of the null node.
     *
     * @return the element type of the null node
     */
    @Override
    public NodeType type() {
        return NodeType.NULL;
    }

    /**
     * Returns a list containing the token of the null node.
     *
     * @return a list containing the token of the null node
     */
    @Override
    public List<Token> tokens() {
        return Collections.singletonList(token);
    }

    /**
     * Returns the root element token of the null node.
     *
     * @return the root element token of the null node
     */
    @Override
    public Token rootElementToken() {
        return token;
    }

    /**
     * Returns the character source associated with the null node.
     *
     * @return the character source associated with the null node
     */
    @Override
    public CharSource charSource() {
        return source;
    }

    /**
     * Returns the length of the null node.
     *
     * @return the length of the null node
     */
    @Override
    public int length() {
        return 4;
    }

    /**
     * Returns the character at the specified index in the null node.
     *
     * @param index the index of the character to retrieve
     * @return the character at the specified index
     * @throws IllegalStateException if the index is out of range
     */
    @Override
    public char charAt(int index) {
        switch (index) {
            case 0:
                return 'n';
            case 1:
                return 'u';
            case 2:
            case 3:
                return 'l';
            default:
                throw new IllegalStateException();
        }
    }

    /**
     * Returns the value of the null node, which is always null.
     *
     * @return the value of the null node
     */
    @Override
    public Object value() {
        return null;
    }

    /**
     * Returns the string representation of the null node.
     *
     * @return the string representation of the null node
     */
    @Override
    public String toString() {
        return "null";
    }

    /**
     * Checks if the null node is equal to the specified object.
     * The null node is considered equal to another object if the other object is also a NullNode.
     *
     * @param o the object to compare with the null node
     * @return true if the null node is equal to the specified object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    /**
     * Returns the hash code value for the null node.
     *
     * @return the hash code value for the null node
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}

```

###### Node.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node;

import io.nats.jparse.Path;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.token.Token;

import java.util.List;
/**
 * This interface represents a Node in the JParse library.
 * <p>
 * A Node is a component of a parsed structure, such as a JSON document.
 * <p>
 * It provides methods to access the characteristics and content of the Node.
 *
 * <p>Each Node has a type, a list of tokens, and a root element token.
 * It also provides information about whether it is a scalar or a collection.
 * <p>
 * Additionally, it offers methods to retrieve the content of the Node in various formats,
 * <p>
 * such as a String, CharSequence, or JSON representation.
 *
 * <p>Implementations of this interface should provide the necessary functionality
 * to handle different types of Nodes, such as ScalarNode and CollectionNode.
 * <p>
 * Custom operations and behaviors can be defined by extending this interface.
 *
 * <p>This interface extends the CharSequence interface, allowing users to treat
 * the Node as a sequence of characters. It also provides methods to access
 * <p>
 * individual characters and substrings within the Node's content.
 *
 * <p>Nodes can be located within a structure using a path. The {@code atPath} method
 * allows retrieving a specific Node at a given path.
 *
 * @see io.nats.jparse.node.ScalarNode
 * @see io.nats.jparse.node.CollectionNode
 * @see io.nats.jparse.Path
 * @see io.nats.jparse.source.CharSource
 * @see io.nats.jparse.token.Token
 */
public interface Node extends CharSequence {

    /**

     Returns the NodeType of the Node.
     @return the NodeType of the Node
     */
    NodeType type();

    /**

     Returns the list of tokens associated with the Node.
     @return the list of tokens associated with the Node
     */
    List<Token> tokens();

    /**

     Returns the root element token of the Node.
     @return the root element token of the Node
     */
    Token rootElementToken();

    /**

     Returns the CharSource associated with the Node.
     @return the CharSource associated with the Node
     */
    CharSource charSource();

    /**

     Checks if the Node is a scalar.
     @return {@code true} if the Node is a scalar, {@code false} otherwise
     */
    boolean isScalar();

    /**

     Checks if the Node is a collection.
     @return {@code true} if the Node is a collection, {@code false} otherwise
     */
    boolean isCollection();

    /**

     Returns this Node as a ScalarNode.
     @return this Node as a ScalarNode
     */
    default ScalarNode asScalar() {
        return (ScalarNode) this;
    }

    /**

     Returns this Node as a CollectionNode.
     @return this Node as a CollectionNode
     */
    default CollectionNode asCollection() {
        return (CollectionNode) this;
    }

    @Override
    default int length() {
        final Token token = rootElementToken();
        return token.endIndex - token.startIndex;
    }

    @Override
    default char charAt(int index) {
        return charSource().getChartAt(rootElementToken().startIndex + index);
    }

    @Override
    default CharSequence subSequence(int start, int end) {
        Token token = rootElementToken();
        return charSource().getCharSequence(start + token.startIndex, end + token.startIndex);
    }

    /**
     * Returns the original content of the Node as a String.
     *
     * @return the original content of the Node as a String
     */
    default String originalString() {
        return charSource().getString(rootElementToken().startIndex, rootElementToken().endIndex);
    }

    /**

     Returns the JSON representation of the Node as a String.
     @return the JSON representation of the Node as a String
     */
    default String toJsonString() {
        return originalString();
    }

    /**

     Returns the original content of the Node as a CharSequence.
     @return the original content of the Node as a CharSequence
     */
    default CharSequence originalCharSequence() {
        return charSource().getCharSequence(rootElementToken().startIndex, rootElementToken().endIndex);
    }

    /**

     Returns the JSON representation of the Node as a CharSequence.
     @return the JSON representation of the Node as a CharSequence
     */
    default CharSequence toJsonCharSequence() {
        return originalCharSequence();
    }

    /**

     Compares the content of the Node with the given String.
     @param content the String to compare with
     @return {@code true} if the content of the Node is equal to the given String, {@code false} otherwise
     */
    default boolean equalsContent(String content) {
        return equals(content);
    }

    /**

     Returns the Node located at the specified path within this Node.
     @param path the path to the desired Node
     @return the Node located at the specified path, or {@code null} if not found
     */
    default Node atPath(String path) {
        return Path.atPath(path, this);
    }
}

```

###### NumberNode.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node;

import io.nats.jparse.node.support.CharSequenceUtils;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.token.Token;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

/**
 * The NumberNode class represents a numeric node in a tree structure.
 * <p>
 * It implements the ScalarNode and CharSequence interfaces.
 * <p>
 * Number nodes can store integer, long, float, and double values.
 */
public class NumberNode extends Number implements ScalarNode, CharSequence {

    /**
     * Token associated with this number node.
     * This token represents a parsed number from the source character sequence.
     */
    private final Token token;

    /**
     * Source of characters used to create the token.
     * This source is used to perform further operations like extracting string or sub-sequence.
     */
    private final CharSource source;

    /**
     * The type of this node.
     * This type defines the type of the value this node represents (integer, float, etc.).
     */
    private final NodeType elementType;

    /**
     * Flag to indicate whether the hash code has been computed or not.
     * It helps to avoid recomputing the hash code for every call of hashCode() method.
     */
    private boolean hashCodeSet;

    /**
     * Cache for the hash code of this object.
     * Once computed, the hash code is stored in this variable for subsequent calls.
     */
    private int hashCode;


    /**
     * Constructs a NumberNode with the specified token, source, and element type.
     *
     * @param token       the token representing the numeric value
     * @param source      the character source containing the numeric value
     * @param elementType the element type of the number node
     */
    public NumberNode(Token token, CharSource source, NodeType elementType) {
        this.token = token;
        this.source = source;
        this.elementType = elementType;
    }

    /**
     * Returns the integer value of the number node.
     *
     * @return the integer value of the number node
     */
    @Override
    public int intValue() {
        return source.getInt(token.startIndex, token.endIndex);
    }

    /**
     * Returns the long value of the number node.
     *
     * @return the long value of the number node
     */
    @Override
    public long longValue() {
        return source.getLong(token.startIndex, token.endIndex);
    }

    /**
     * Returns the float value of the number node.
     *
     * @return the float value of the number node
     */
    @Override
    public float floatValue() {
        return source.getFloat(token.startIndex, token.endIndex);
    }

    /**
     * Returns the double value of the number node.
     *
     * @return the double value of the number node
     */
    @Override
    public double doubleValue() {
        return source.getDouble(token.startIndex, token.endIndex);
    }

    /**
     * Returns the BigDecimal value of the number node.
     *
     * @return the BigDecimal value of the number node
     */
    public BigDecimal bigDecimalValue() {
        return source.getBigDecimal(token.startIndex, token.endIndex);
    }

    /**
     * Returns the BigInteger value of the number node.
     *
     * @return the BigInteger value of the number node
     */
    public BigInteger bigIntegerValue() {
        return source.getBigInteger(token.startIndex, token.endIndex);
    }

    /**
     * Returns the value of the number node as an Object.
     * If the number node represents an integer, an Integer object is returned.
     * If the number node represents a long, a Long object is returned.
     * Otherwise, a Double object is returned.
     *
     * @return the value of the number node as an Object
     */
    @Override
    public Object value() {
        if (isInteger()) {
            return intValue();
        } else if (isLong()) {
            return longValue();
        } else {
            return this.doubleValue();
        }
    }

    /**
     * Returns the element type of the number node.
     *
     * @return the element type of the number node
     */
    @Override
    public NodeType type() {
        return this.elementType;
    }

    /**
     * Returns the length of the number node.
     *
     * @return the length of the number node
     */
    @Override
    public int length() {
        return token.endIndex - token.startIndex;
    }

    /**
     * Returns the character at the specified index in the number node.
     *
     * @param index the index of the character to retrieve
     * @return the character at the specified index
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     */
    @Override
    public char charAt(int index) {
        if (index > length()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return source.getChartAt(token.startIndex + index);
    }

    /**
     * Returns a CharSequence that is a subsequence of the number node.
     *
     * @param start the start index of the subsequence (inclusive)
     * @param end   the end index of the subsequence (exclusive)
     * @return the subsequence of the number node
     * @throws IndexOutOfBoundsException if the start or end index is out of range
     */
    @Override
    public CharSequence subSequence(int start, int end) {
        if (end > length()) {
            throw new IndexOutOfBoundsException();
        }
        return source.getCharSequence(start + token.startIndex, end + token.startIndex);
    }

    /**
     * Returns a list containing the token of the number node.
     *
     * @return a list containing the token of the number node
     */
    @Override
    public List<Token> tokens() {
        return Collections.singletonList(this.token);
    }

    /**
     * Returns the root element token of the number node.
     *
     * @return the root element token of the number node
     */
    @Override
    public Token rootElementToken() {
        return token;
    }

    /**
     * Returns the character source associated with the number node.
     *
     * @return the character source associated with the number node
     */
    @Override
    public CharSource charSource() {
        return source;
    }

    /**
     * Checks if the number node is equal to the specified object.
     * <p>
     * The number node is considered equal to another object if:
     * <p>
     * The other object is an instance of NumberNode and has the same CharSequence representation.
     * The other object is an instance of Number and has the same numeric value.
     *
     * @param o the object to compare with the number node
     * @return true if the number node is equal to the specified object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (o instanceof NumberNode) {
            NumberNode other = (NumberNode) o;
            return CharSequenceUtils.equals(this, other);
        } else if (o instanceof Number) {
            switch (o.getClass().getName()) {
                case "java.lang.Integer":
                    return this.intValue() == (int) o;
                case "java.lang.Long":
                    return this.longValue() == (long) o;
                case "java.lang.Float":
                    return this.floatValue() == (float) o;
                case "java.lang.Double":
                    return this.doubleValue() == (double) o;
                case "java.math.BigDecimal":
                    return this.bigDecimalValue().equals(o);
                case "java.math.BigInteger":
                    return this.bigIntegerValue().equals(o);
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Returns the hash code value for the number node.
     *
     * @return the hash code value for the number node
     */
    @Override
    public int hashCode() {
        if (hashCodeSet) {
            return hashCode;
        }
        hashCode = CharSequenceUtils.hashCode(this);
        hashCodeSet = true;
        return hashCode;
    }

    /**
     * Checks if the number node represents an integer value.
     *
     * @return true if the number node represents an integer value, false otherwise
     */
    public boolean isInteger() {
        switch (elementType) {
            case INT:
                return source.isInteger(this.token.startIndex, this.token.endIndex);
            default:
                return false;
        }
    }

    /**
     * Checks if the number node represents a long value.
     *
     * @return true if the number node represents a long value, false otherwise
     */
    public boolean isLong() {
        switch (elementType) {
            case INT:
                return !source.isInteger(this.token.startIndex, this.token.endIndex);
            default:
                return false;
        }
    }

    /**
     * Returns the string representation of the number node.
     *
     * @return the string representation of the number node
     */
    @Override
    public String toString() {
        return this.originalString();
    }
}

```

###### ScalarNode.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * The ScalarNode interface represents a scalar node in a tree structure.
 * <p>
 * Scalar nodes are leaf nodes that contain a single value.
 * <p>
 * Implementations of this interface should provide methods to access and manipulate the value of the scalar node.
 * <p>
 * The value can be of various types, including boolean, integer, long, double, BigDecimal, BigInteger,
 * CharSequence, and String.
 */
public interface ScalarNode extends Node {

    @Override
    default boolean isScalar() {
        return true;
    }

    @Override
    default boolean isCollection() {
        return false;
    }

    /**
     * Returns the value of the scalar node.
     *
     * @return the value of the scalar node
     */
    Object value();

    /**
     * Returns the boolean value of the scalar node.
     * This method should be implemented by concrete classes
     * that represent boolean scalar nodes.
     *
     * @return the boolean value of the scalar node
     * @throws UnsupportedOperationException if the method is not supported by the implementation
     */
    default boolean booleanValue() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the integer value of the scalar node.
     * This method should be implemented by concrete classes
     * that represent integer scalar nodes.
     *
     * @return the integer value of the scalar node
     * @throws UnsupportedOperationException if the method is not supported by the implementation
     */
    default int intValue() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the long value of the scalar node.
     * This method should be implemented by concrete classes
     * that represent long scalar nodes.
     *
     * @return the long value of the scalar node
     * @throws UnsupportedOperationException if the method is not supported by the implementation
     */
    default long longValue() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the double value of the scalar node.
     * This method should be implemented by concrete classes
     * that represent double scalar nodes.
     *
     * @return the double value of the scalar node
     * @throws UnsupportedOperationException if the method is not supported by the implementation
     */
    default double doubleValue() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the BigDecimal value of the scalar node.
     * This method should be implemented by concrete classes
     * that represent BigDecimal scalar nodes.
     *
     * @return the BigDecimal value of the scalar node
     * @throws UnsupportedOperationException if the method is not supported by the implementation
     */
    default BigDecimal bigDecimalValue() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the BigInteger value of the scalar node.
     * This method should be implemented by concrete classes
     * that represent BigInteger scalar nodes.
     *
     * @return the BigInteger value of the scalar node
     * @throws UnsupportedOperationException if the method is not supported by the implementation
     */
    default BigInteger bigIntegerValue() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the CharSequence value of the scalar node.
     * This method should be implemented by concrete classes
     * that represent CharSequence scalar nodes.
     *
     * @return the CharSequence value of the scalar node
     */
    default CharSequence charSequenceValue() {
        return this.originalCharSequence();
    }

    /**
     * Returns the String value of the scalar node.
     * This method should be implemented by concrete classes
     * that represent String scalar nodes.
     *
     * @return the String value of the scalar node
     */
    default String stringValue() {
        return this.originalString();
    }

    /**
     * Checks if the scalar node is equal to the specified string.
     *
     * @param str the string to compare with
     * @return {@code true} if the scalar node is equal to the specified string, {@code false} otherwise
     */
    default boolean equalsString(String str) {
        return this.equals(str);
    }

}

```

###### StringNode.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node;

import io.nats.jparse.node.support.CharSequenceUtils;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.token.Token;

import java.util.Collections;
import java.util.List;

/**
 * This class represents a StringNode in the JParse library.
 * It is a specialized ScalarNode that stores a string value.
 *
 * <p>The StringNode contains information about the token, source, start and end indices,
 * and whether the string should be encoded by default.
 *
 * <p>It provides methods to access the string value and perform operations on the string,
 * such as getting the length, retrieving characters, and creating substrings.
 *
 * <p>The StringNode implements the CharSequence interface and overrides the equals and hashCode methods
 * for proper comparison and hashing of the string value.
 *
 * @see io.nats.jparse.node.ScalarNode
 * @see java.lang.CharSequence
 */
public class StringNode implements ScalarNode, CharSequence {

    private final Token token;
    private final CharSource source;
    private final int length;
    private final int start;
    private final int end;
    private final boolean encodeStringByDefault;
    private int hashCode = 0;
    private boolean hashCodeSet = false;

    @Override
    public NodeType type() {
        return NodeType.STRING;
    }

    @Override
    public List<Token> tokens() {
        return Collections.singletonList(this.token);
    }

    @Override
    public Token rootElementToken() {
        return token;
    }

    @Override
    public CharSource charSource() {
        return source;
    }

    @Override
    public Object value() {
        return toString();
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public char charAt(int index) {
        return source.getChartAt(token.startIndex + index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return source.getCharSequence(start + this.start, end + this.start);
    }

    /**
     * Constructs a StringNode with the specified token and source.
     *
     * @param token                 the token representing the string
     * @param source                the source containing the string
     * @param encodeStringByDefault flag indicating whether the string should be encoded by default
     */
    public StringNode(Token token, CharSource source, boolean encodeStringByDefault) {
        this.token = token;
        this.source = source;
        start = token.startIndex;
        end = token.endIndex;
        this.encodeStringByDefault = encodeStringByDefault;
        this.length = token.endIndex - token.startIndex;
    }

    /**
     * Constructs a StringNode with the specified token and source.
     * The string is encoded by default.
     *
     * @param token  the token representing the string
     * @param source the source containing the string
     */
    public StringNode(Token token, CharSource source) {
        this.token = token;
        this.source = source;
        start = token.startIndex;
        end = token.endIndex;
        this.encodeStringByDefault = true;
        this.length = token.endIndex - token.startIndex;
    }


    /**
     * Returns a CharSequence representing the content of the StringNode.
     *
     * @return a CharSequence representing the content of the StringNode
     */
    public CharSequence charSequence() {
        return source.getCharSequence(this.start, this.end);
    }

    /**
     * Returns a String representation of the StringNode.
     * If encodeStringByDefault is true, the string will be encoded using the source's encoding if needed.
     * Otherwise, the original string will be returned.
     *
     * @return a String representation of the StringNode
     */
    @Override
    public String toString() {
        return encodeStringByDefault ? source.toEncodedStringIfNeeded(start, end) : source.getString(start, end);
    }

    /**
     * Returns the encoded string representation of the StringNode.
     * The string is encoded using the source's encoding.
     *
     * @return the encoded string representation of the StringNode
     */
    public String toEncodedString() {
        return source.getEncodedString(start, end);
    }

    /**
     * Returns the unencoded string representation of the StringNode.
     * The original string is returned without encoding.
     *
     * @return the unencoded string representation of the StringNode
     */
    public String toUnencodedString() {
        return source.getString(start, end);
    }

    /**
     * Checks if the StringNode is equal to the specified object.
     * The comparison is performed by comparing the content as a CharSequence.
     *
     * @param o the object to compare with
     * @return {@code true} if the StringNode is equal to the object, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof CharSequence) {
            CharSequence other = (CharSequence) o;
            return CharSequenceUtils.equals(this, other);
        } else {
            return false;
        }
    }

    /**
     * Returns the hash code value for the StringNode.
     * The hash code is calculated based on the content as a CharSequence.
     *
     * @return the hash code value for the StringNode
     */
    @Override
    public int hashCode() {
        if (hashCodeSet) {
            return hashCode;
        }
        hashCode = CharSequenceUtils.hashCode(this);
        hashCodeSet = true;
        return hashCode;
    }
}

```

###### RootNode.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node;

import io.nats.jparse.node.support.NodeUtils;
import io.nats.jparse.node.support.TokenList;
import io.nats.jparse.node.support.TokenSubList;
import io.nats.jparse.path.PathNode;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.token.Token;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static io.nats.jparse.token.TokenTypes.ARRAY_TOKEN;
import static io.nats.jparse.token.TokenTypes.OBJECT_TOKEN;


/**
 * The RootNode class represents the root node of a tree structure.
 * <p>
 * It serves as the entry point for accessing and manipulating the contents of the tree.
 * <p>
 * The root node can be either an object node or an array node.
 */
public class RootNode implements CollectionNode {

    private final TokenList tokens;
    private final CharSource source;
    private final Token rootToken;
    private final boolean objectsKeysCanBeEncoded;

    private Node root;

    /**
     * Constructs a RootNode with the specified tokens, character source, and objectsKeysCanBeEncoded flag.
     *
     * @param tokens                  the list of tokens representing the tree structure
     * @param source                  the character source from which the tokens were parsed
     * @param objectsKeysCanBeEncoded a flag indicating whether object keys can be encoded
     */
    public RootNode(TokenList tokens, CharSource source, boolean objectsKeysCanBeEncoded) {
        this.tokens = tokens;
        this.source = source;
        this.rootToken = tokens.get(0);
        this.objectsKeysCanBeEncoded = objectsKeysCanBeEncoded;
    }

    /**
     * Returns the type of the root node.
     *
     * @return the type of the root node
     */
    public NodeType getType() {
        return NodeType.tokenTypeToElement(rootToken.type);
    }

    /**
     * Returns the node associated with the specified key.
     * This method delegates the call to the appropriate method of the underlying node.
     *
     * @param key the key to retrieve the associated node
     * @return the node associated with the specified key
     */
    @Override
    public Node getNode(Object key) {
        switch (rootToken.type) {
            case OBJECT_TOKEN:
                return getObjectNode().getNode(key);
            case ARRAY_TOKEN:
                return getArrayNode().getNode(key);
            default:
                return doGetNode(key);
        }
    }

    /**
     * Returns the list of tokens representing the children of the root node.
     * This method delegates the call to the appropriate method of the underlying node.
     *
     * @return the list of tokens representing the children of the root node
     */
    @Override
    public List<List<Token>> childrenTokens() {
        switch (rootToken.type) {
            case OBJECT_TOKEN:
                return getObjectNode().childrenTokens();
            case ARRAY_TOKEN:
                return getArrayNode().childrenTokens();
            default:
                return doGetChildrenTokens();
        }
    }

    private List<List<Token>> doGetChildrenTokens() {
        return ((CollectionNode) getNode()).childrenTokens();
    }

    private Node doGetNode(Object key) {
        return ((CollectionNode) getNode()).getNode(key);
    }

    /**
     * Returns the root node of the tree.
     * If the root node has not been created yet, it will be lazily created.
     *
     * @return the root node of the tree
     */
    public Node getNode() {
        if (root == null) {
            root = NodeUtils.createNode(new TokenSubList(tokens.getTokens(), 0, tokens.size()), source, objectsKeysCanBeEncoded);
        }
        return root;
    }

    /**
     * Returns the path node associated with the root node.
     * If the root node has not been created yet, it will be lazily created.
     *
     * @return the path node associated with the root node
     */
    public PathNode getPathNode() {
        if (root == null) {
            root = new PathNode((TokenSubList) tokens.subList(0, tokens.size()), charSource());
        }
        return (PathNode) root;
    }

    /**
     * Returns the object node associated with the root node.
     *
     * @return the object node associated with the root node
     */
    public ObjectNode getObjectNode() {
        return (ObjectNode) getNode();
    }

    @Override
    public ArrayNode asArray() {
        return getArrayNode();
    }

    @Override
    public ObjectNode asObject() {
        return getObjectNode();
    }

    /**
     * Returns the map representation of the root node.
     * This method assumes that the root node is an object node.
     *
     * @return the map representation of the root node
     */
    public Map<String, Object> getMap() {
        return (Map<String, Object>) (Object) getObjectNode();
    }

    /**
     * Returns the string node associated with the root node.
     * This method assumes that the root node is a string node.
     *
     * @return the string node associated with the root node
     */
    public StringNode getStringNode() {
        return (StringNode) this.getNode();
    }

    /**
     * Returns the string representation of the root node.
     * This method assumes that the root node is a string node.
     *
     * @return the string representation of the root node
     */
    public String getString() {
        return getStringNode().toString();
    }

    /**
     * Returns the integer value of the root node.
     * This method assumes that the root node is a number node.
     *
     * @return the integer value of the root node
     */
    public int getInt() {
        return getNumberNode().intValue();
    }

    /**
     * Returns the float value of the root node.
     * This method assumes that the root node is a number node.
     *
     * @return the float value of the root node
     */
    public float getFloat() {
        return getNumberNode().floatValue();
    }

    /**
     * Returns the long value of the root node.
     * This method assumes that the root node is a number node.
     *
     * @return the long value of the root node
     */
    public long getLong() {
        return getNumberNode().longValue();
    }

    /**
     * Returns the double value of the root node.
     * This method assumes that the root node is a number node.
     *
     * @return the double value of the root node
     */
    public double getDouble() {
        return getNumberNode().doubleValue();
    }

    /**
     * Returns the BigDecimal value of the root node.
     * This method assumes that the root node is a number node.
     *
     * @return the BigDecimal value of the root node
     */
    public BigDecimal getBigDecimal() {
        return getNumberNode().bigDecimalValue();
    }

    /**
     * Returns the BigInteger value of the root node.
     * This method assumes that the root node is a number node.
     *
     * @return the BigInteger value of the root node
     */
    public BigInteger getBigIntegerValue() {
        return getNumberNode().bigIntegerValue();
    }

    /**
     * Returns the number node associated with the root node.
     * This method assumes that the root node is a number node.
     *
     * @return the number node associated with the root node
     */
    public NumberNode getNumberNode() {
        return (NumberNode) getNode();
    }

    /**
     * Returns the boolean node associated with the root node.
     * This method assumes that the root node is a boolean node.
     *
     * @return the boolean node associated with the root node
     */
    public BooleanNode getBooleanNode() {
        return (BooleanNode) getNode();
    }

    /**
     * Returns the null node associated with the root node.
     * This method assumes that the root node is a null node.
     *
     * @return the null node associated with the root node
     */
    public NullNode getNullNode() {
        return (NullNode) getNode();
    }

    /**
     * Returns the boolean value of the root node.
     * This method assumes that the root node is a boolean node.
     *
     * @return the boolean value of the root node
     */
    public boolean getBoolean() {
        return getBooleanNode().booleanValue();
    }

    /**
     * Get this as an array node.
     * @return array node
     */
    public ArrayNode getArrayNode() {
        return (ArrayNode) getNode();
    }

    /**
     * Returns the type of the root node, which is ROOT.
     *
     * @return the type of the root node
     */
    @Override
    public NodeType type() {
        return NodeType.ROOT;
    }

    /**
     * Returns the list of tokens associated with the root node.
     *
     * @return the list of tokens associated with the root node
     */
    @Override
    public List<Token> tokens() {
        return this.tokens;
    }

    /**
     * Returns the root element token of the root node.
     *
     * @return the root element token of the root node
     */
    @Override
    public Token rootElementToken() {
        return rootToken;
    }

    /**
     * Returns the character source from which the tokens were parsed.
     *
     * @return the character source from which the tokens were parsed
     */
    @Override
    public CharSource charSource() {
        return source;
    }

    /**
     * Checks if this root node is equal to the specified object.
     * Two root nodes are considered equal if their underlying nodes are equal.
     *
     * @param o the object to compare with
     * @return {@code true} if this root node is equal to the specified object, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof RootNode) {
            RootNode other = (RootNode) o;
            return getNode().equals(other.getNode());
        }
        return false;
    }

    /**
     * Returns the hash code value for this root node.
     *
     * @return the hash code value for this root node
     */
    @Override
    public int hashCode() {
        return getNode().hashCode();
    }
}

```

###### ObjectNode.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node;

import io.nats.jparse.node.support.NodeUtils;
import io.nats.jparse.node.support.TokenSubList;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.token.Token;
import io.nats.jparse.token.TokenTypes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The ObjectNode class represents an object node in a tree structure.
 * <p>
 * It extends the AbstractMap class and implements the CollectionNode interface.
 * <p>
 * Object nodes are used to store key-value pairs, where the keys are CharSequences
 * <p>
 * and the values are nodes in the tree structure.
 */
public class ObjectNode extends AbstractMap<CharSequence, Node> implements CollectionNode {

    private final TokenSubList tokens;
    private final CharSource source;
    private final Token rootToken;
    private final boolean objectsKeysCanBeEncoded;
    private List<List<Token>> childrenTokens;
    private Map<Object, Node> elementMap;
    private List<CharSequence> keys;
    private boolean hashCodeSet;
    private int hashCode;

    /**
     * Constructs an ObjectNode with the specified tokens, character source, and objectsKeysCanBeEncoded flag.
     *
     * @param tokens                  the list of tokens representing the object node
     * @param source                  the character source from which the tokens were parsed
     * @param objectsKeysCanBeEncoded a flag indicating whether object keys can be encoded
     */
    public ObjectNode(TokenSubList tokens, CharSource source, boolean objectsKeysCanBeEncoded) {
        this.tokens = tokens;
        this.source = source;
        this.rootToken = tokens.get(0);
        this.objectsKeysCanBeEncoded = objectsKeysCanBeEncoded;
    }

    /**
     * Returns the list of tokens representing the children of the object node.
     * If the list of children tokens has not been initialized yet, it will be lazily initialized.
     *
     * @return the list of tokens representing the children of the object node
     */
    @Override
    public List<List<Token>> childrenTokens() {
        if (childrenTokens == null) {
            childrenTokens = NodeUtils.getChildrenTokens(tokens);
        }
        return childrenTokens;
    }

    /**
     * Returns the node associated with the specified key.
     *
     * @param key the key to retrieve the associated node
     * @return the node associated with the specified key
     */
    @Override
    public Node getNode(Object key) {
        return lookupElement((CharSequence) key);
    }

    /**
     * Returns the keys of the object node as a list of CharSequences.
     * If the keys list has not been initialized yet, it will be lazily initialized.
     *
     * @return the keys of the object node as a list of CharSequences
     */
    public List<CharSequence> getKeys() {
        return keys();
    }

    /**
     * Returns the number of key-value pairs in the object node.
     *
     * @return the number of key-value pairs in the object node
     */
    public int length() {
        return childrenTokens().size() / 2;
    }

    /**
     * Returns the type of the object node, which is OBJECT.
     *
     * @return the type of the object node
     */
    @Override
    public NodeType type() {
        return NodeType.OBJECT;
    }

    /**
     * Returns the list of tokens associated with the object node.
     *
     * @return the list of tokens associated with the object node
     */
    @Override
    public List<Token> tokens() {
        return tokens;
    }

    /**
     * Returns the root element token of the object node.
     *
     * @return the root element token of the object node
     */
    @Override
    public Token rootElementToken() {
        return rootToken;
    }

    /**
     * Returns the character source from which the tokens were parsed.
     *
     * @return the character source from which the tokens were parsed
     */
    @Override
    public CharSource charSource() {
        return source;
    }

    /**
     * Returns the value associated with the specified key.
     * If the value is a NullNode, null is returned instead.
     *
     * @param key the key to retrieve the associated value
     * @return the value associated with the specified key, or null if the value is a NullNode
     */
    @Override
    public Node get(Object key) {
        final Node value = getNode(key);
        return value instanceof NullNode ? null : value;
    }

    /**
     * Checks if the object node contains the specified key.
     *
     * @param key the key to check for existence
     * @return {@code true} if the object node contains the key, {@code false} otherwise
     */
    public boolean containsKey(Object key) {
        return lookupElement((CharSequence) key) != null;
    }

    /**
     * Checks if the object node is empty (contains no key-value pairs).
     *
     * @return {@code true} if the object node is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return keys().isEmpty();
    }

    /**
     * Returns the number of key-value pairs in the object node.
     *
     * @return the number of key-value pairs in the object node
     */
    @Override
    public int size() {
        return keys().size();
    }

    /**
     * Returns a set of the keys in the object node.
     *
     * @return a set of the keys in the object node
     */
    @Override
    public Set<CharSequence> keySet() {
        return keys().stream().map(CharSequence::toString).collect(Collectors.toSet());
    }

    /**
     * Returns a collection of the values in the object node.
     *
     * @return a collection of the values in the object node
     */
    @Override
    public Collection<Node> values() {
        return keys().stream().map(this::lookupElement).collect(Collectors.toList());
    }

    /**
     * Returns a set view of the entries in the object node.
     *
     * @return a set view of the entries in the object node
     */
    @Override
    public Set<Entry<CharSequence, Node>> entrySet() {


        return new AbstractSet<Entry<CharSequence, Node>>() {

            /**
             * Checks if the object node contains the specified entry.
             *
             * @param o the entry to check for existence
             * @return {@code true} if the object node contains the entry, {@code false} otherwise
             */
            @Override
            public boolean contains(Object o) {
                return keys().contains(o);
            }

            /**
             * Returns an iterator over the entries in the object node.
             *
             * @return an iterator over the entries in the object node
             */
            @Override
            public Iterator<Entry<CharSequence, Node>> iterator() {
                final Iterator<CharSequence> iterator = keys().iterator();
                return new Iterator<Entry<CharSequence, Node>>() {
                    @Override
                    public boolean hasNext() {
                        return iterator.hasNext();
                    }

                    @Override
                    public Entry<CharSequence, Node> next() {
                        final CharSequence nextKey = iterator.next().toString();
                        return new Entry<CharSequence, Node>() {
                            @Override
                            public CharSequence getKey() {
                                return nextKey;
                            }

                            @Override
                            public Node getValue() {
                                return lookupElement(nextKey);
                            }

                            @Override
                            public Node setValue(Node value) {
                                throw new UnsupportedOperationException();
                            }
                        };
                    }
                };
            }

            /**
             * Returns the number of entries in the object node.
             *
             * @return the number of entries in the object node
             */
            @Override
            public int size() {
                return keys().size();
            }
        };

    }

    /**
     * Checks if this object node is equal to the specified object.
     * <p>
     * Two object nodes are considered equal if they have the same keys and corresponding values.
     *
     * @param o the object to compare with
     * @return {@code true} if this object node is equal to the specified object, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObjectNode)) return false;

        final ObjectNode other = (ObjectNode) o;

        if (this.tokens.size() != other.tokens.size()) {
            return false;
        }


        final List<CharSequence> keys = keys();
        final List<CharSequence> otherKeys = other.keys();

        if (keys.size() != otherKeys.size()) {
            return false;
        }

        for (Object key : keys) {
            final Node otherElementValue = other.getNode(key);
            final Node thisElementValue = this.getNode(key);

            if (otherElementValue == null) {
                return false;
            }

            if (!otherElementValue.equals(thisElementValue)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the hash code value for this object node.
     *
     * @return the hash code value for this object node
     */
    @Override
    public int hashCode() {
        if (hashCodeSet) {
            return hashCode;
        }
        final List<CharSequence> keys = keys();
        for (Object key : keys) {
            this.getNode(key);
        }
        hashCode = this.elementMap.hashCode();
        hashCodeSet = true;
        return hashCode;
    }

    /**
     * Returns the number node associated with the specified key.
     *
     * @param key the key to retrieve the associated number node
     * @return the number node associated with the specified key
     */
    public NumberNode getNumberNode(CharSequence key) {
        return (NumberNode) getNode(key);
    }

    /**
     * Returns the null node associated with the specified key.
     *
     * @param key the key to retrieve the associated null node
     * @return the null node associated with the specified key
     */
    public NullNode getNullNode(CharSequence key) {
        return (NullNode) getNode(key);
    }

    /**
     * Returns the long value associated with the specified key.
     * This method assumes that the value associated with the key is a number node.
     *
     * @param key the key to retrieve the associated long value
     * @return the long value associated with the specified key
     */
    public long getLong(CharSequence key) {
        return ((NumberNode) getNode(key)).longValue();
    }

    /**
     * Returns the double value associated with the specified key.
     * This method assumes that the value associated with the key is a number node.
     *
     * @param key the key to retrieve the associated double value
     * @return the double value associated with the specified key
     */
    public double getDouble(CharSequence key) {
        return ((NumberNode) getNode(key)).doubleValue();
    }

    /**
     * Returns the integer value associated with the specified key.
     * This method assumes that the value associated with the key is a number node.
     *
     * @param key the key to retrieve the associated integer value
     * @return the integer value associated with the specified key
     */
    public int getInt(CharSequence key) {
        return ((NumberNode) getNode(key)).intValue();
    }

    /**
     * Returns the float value associated with the specified key.
     * This method assumes that the value associated with the key is a number node.
     *
     * @param key the key to retrieve the associated float value
     * @return the float value associated with the specified key
     */
    public float getFloat(CharSequence key) {
        return ((NumberNode) getNode(key)).floatValue();
    }

    /**
     * Returns the BigDecimal value associated with the specified key.
     * This method assumes that the value associated with the key is a number node.
     *
     * @param key the key to retrieve the associated BigDecimal value
     * @return the BigDecimal value associated with the specified key
     */
    public BigDecimal getBigDecimal(CharSequence key) {
        return getNumberNode(key).bigDecimalValue();
    }

    /**
     * Returns the BigInteger value associated with the specified key.
     * This method assumes that the value associated with the key is a number node.
     *
     * @param key the key to retrieve the associated BigInteger value
     * @return the BigInteger value associated with the specified key
     */
    public BigInteger getBigInteger(CharSequence key) {
        return getNumberNode(key).bigIntegerValue();
    }

    /**
     * Returns the string node associated with the specified key.
     *
     * @param key the key to retrieve the associated string node
     * @return the string node associated with the specified key
     */
    public StringNode getStringNode(CharSequence key) {
        return (StringNode) getNode(key);
    }

    /**
     * Returns the string representation of the value associated with the specified key.
     * This method assumes that the value associated with the key is a string node.
     *
     * @param key the key to retrieve the associated string value
     * @return the string representation of the value associated with the specified key
     */
    public String getString(CharSequence key) {
        return getStringNode(key).toString();
    }

    /**
     * Returns the object node associated with the specified key.
     *
     * @param key the key to retrieve the associated object node
     * @return the object node associated with the specified key
     */
    public ObjectNode getObjectNode(CharSequence key) {
        return (ObjectNode) getNode(key);
    }

    /**
     * Returns the array node associated with the specified key.
     *
     * @param key the key to retrieve the associated array node
     * @return the array node associated with the specified key
     */
    public ArrayNode getArrayNode(CharSequence key) {
        return (ArrayNode) getNode(key);
    }

    /**
     * Returns the boolean node associated with the specified key.
     *
     * @param key the key to retrieve the associated boolean node
     * @return the boolean node associated with the specified key
     */
    public BooleanNode getBooleanNode(CharSequence key) {
        return (BooleanNode) getNode(key);
    }

    /**
     * Returns the boolean value associated with the specified key.
     * This method assumes that the value associated with the key is a boolean node.
     *
     * @param key the key to retrieve the associated boolean value
     * @return the boolean value associated with the specified key
     */
    public boolean getBoolean(CharSequence key) {
        return getBooleanNode(key).booleanValue();
    }

    /**
     * Returns an Optional containing the node associated with the specified key.
     * If a node is found with a matching key, it is created and returned as an Optional.
     * If no matching key is found, an empty Optional is returned.
     *
     * @param key the node representing the key to search for
     * @return an Optional containing the node associated with the specified key, or an empty Optional if no matching key is found
     */
    public Optional<Node> getNode(final Node key) {
        List<List<Token>> childrenTokens = childrenTokens();
        Node node = null;
        for (int index = 0; index < childrenTokens.size(); index += 2) {
            List<Token> itemKey = childrenTokens.get(index);
            if (keyMatch(itemKey, key)) {
                node = NodeUtils.createNodeForObject(childrenTokens.get(index + 1), source, objectsKeysCanBeEncoded);
                break;
            }
        }
        return Optional.ofNullable(node);
    }

    /**
     * Checks if the given list of tokens matches the specified key node.
     *
     * @param tokens the list of tokens representing the key
     * @param key    the node representing the key to match
     * @return {@code true} if the list of tokens matches the specified key node, {@code false} otherwise
     */
    private boolean keyMatch(final List<Token> tokens, final Node key) {
        return NodeUtils.createNodeForObject(tokens, source, objectsKeysCanBeEncoded).equals(key);
    }

    /**
     * Looks up and returns the node associated with the specified key.
     * <p>
     * If the node is found in the element map, it is returned.
     * <p>
     * Otherwise, it iterates over the children tokens to find a matching key, creates the corresponding node,
     * <p>
     * adds it to the element map, and returns it.
     *
     * @param key the key to retrieve the associated node
     * @return the node associated with the specified key, or null if no matching key is found
     */
    private Node lookupElement(final CharSequence key) {
        if (elementMap == null) {
            elementMap = new HashMap<>();
        }
        Node node = elementMap.get(key);

        if (node == null) {
            List<List<Token>> childrenTokens = childrenTokens();
            for (int index = 0; index < childrenTokens.size(); index += 2) {
                List<Token> itemKey = childrenTokens.get(index);
                if (doesMatchKey(itemKey, key)) {
                    node = NodeUtils.createNodeForObject(childrenTokens.get(index + 1), source, objectsKeysCanBeEncoded);
                    elementMap.put(key, node);
                    break;
                }
            }
        }
        return node;
    }

    /**
     * Checks if the given list of tokens matches the specified key.
     *
     * @param itemKey the list of tokens representing the key
     * @param key     the CharSequence key to match
     * @return {@code true} if the list of tokens matches the specified key, {@code false} otherwise
     */
    private boolean doesMatchKey(final List<Token> itemKey, final CharSequence key) {

        final Token keyToken = itemKey.get(1);

        if (keyToken.type == TokenTypes.STRING_TOKEN) {
            if (keyToken.length() != key.length()) {
                return false;
            }

            if (objectsKeysCanBeEncoded) {
                final StringNode stringNode = new StringNode(keyToken, source, objectsKeysCanBeEncoded);
                final String string = stringNode.toString();
                for (int index = 0; index < key.length(); index++) {
                    if (string.charAt(index) != key.charAt(index)) {
                        return false;
                    }
                }
                return true;
            } else {
                return source.matchChars(keyToken.startIndex, keyToken.endIndex, key);
            }
        }
        return false;
    }


    private List<CharSequence> keys() {
        if (keys == null) {
            List<List<Token>> childrenTokens = childrenTokens();
            keys = new ArrayList<>(childrenTokens.size() / 2);
            for (int index = 0; index < childrenTokens.size(); index += 2) {
                List<Token> itemKey = childrenTokens.get(index);
                Token keyToken = itemKey.get(1);
                switch (keyToken.type) {
                    case TokenTypes.STRING_TOKEN:
                        final StringNode element = new StringNode(keyToken, source, objectsKeysCanBeEncoded);
                        keys.add(element);
                        break;
                    default:
                        throw new IllegalStateException("Only String are allowed for keys " + TokenTypes.getTypeName(keyToken.type));
                }
                ;

            }
        }
        return keys;
    }

    @Override
    public String toString() {
        return this.originalString();
    }
}

```

###### ArrayNode.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node;

import io.nats.jparse.node.support.NodeUtils;
import io.nats.jparse.node.support.TokenSubList;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.token.Token;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The ArrayNode class represents an array node in a tree structure.
 * <p>
 * It extends the AbstractList class and implements the CollectionNode interface.
 */
public class ArrayNode extends AbstractList<Node> implements CollectionNode {

    private final TokenSubList tokens;
    private final CharSource source;
    private final Token rootToken;
    private final boolean objectsKeysCanBeEncoded;
    private int hashCode;
    private List<List<Token>> childrenTokens;
    private Node[] elements;
    private boolean hashCodeSet;

    /**
     * Constructs an ArrayNode with the specified tokens, source, and objectsKeysCanBeEncoded flag.
     *
     * @param tokens                  the sublist of tokens representing the array node
     * @param source                  the character source containing the array node
     * @param objectsKeysCanBeEncoded flag indicating if object keys can be encoded
     */
    public ArrayNode(final TokenSubList tokens, final CharSource source, boolean objectsKeysCanBeEncoded) {
        this.tokens = tokens;
        this.rootToken = tokens.get(0);
        this.source = source;
        this.objectsKeysCanBeEncoded = objectsKeysCanBeEncoded;
    }

    /**
     * Returns a list containing the children tokens of the array node.
     *
     * @return a list containing the children tokens of the array node
     */
    @Override
    public List<List<Token>> childrenTokens() {
        if (childrenTokens == null) {
            childrenTokens = NodeUtils.getChildrenTokens(tokens);
        }
        return childrenTokens;
    }

    Node[] elements() {
        if (elements == null) {
            elements = new Node[childrenTokens().size()];
        }
        return elements;
    }

    /**
     * Returns the node associated with the specified key in the array node.
     *
     * @param key the key to retrieve the node for
     * @return the node associated with the specified key
     */
    @Override
    public Node getNode(Object key) {
        return key instanceof String ?
                this.getNodeAt(Integer.valueOf((String) key)) :
                this.getNodeAt((Integer) key);
    }

    /**
     * Returns the node at the specified index in the array node.
     *
     * @param index the index of the node to retrieve
     * @return the node at the specified index
     */
    public Node getNodeAt(int index) {
        Node element = elements()[index];
        if (element == null) {
            List<Token> tokens = childrenTokens().get(index);
            elements()[index] = NodeUtils.createNode(tokens, source, objectsKeysCanBeEncoded);
        }
        return elements()[index];
    }

    /**
     * Returns an optional containing the node at the specified index in the array node.
     *
     * @param index the index of the node to retrieve
     * @return an optional containing the node at the specified index, or an empty optional if no node exists at the index
     */
    public Optional<Node> lookupNodeAt(int index) {
        return Optional.ofNullable(getNodeAt(index));
    }

    /**
     * Returns the long value of the node at the specified index in the array node.
     *
     * @param index the index of the node to retrieve
     * @return the long value of the node at the specified index
     */
    public long getLong(int index) {
        return getNumberNode(index).longValue();
    }

    /**
     * Returns the double value of the node at the specified index in the array node.
     *
     * @param index the index of the node to retrieve
     * @return the double value of the node at the specified index
     */
    public double getDouble(int index) {
        return getNumberNode(index).doubleValue();
    }

    /**
     * Returns an array containing the double values of the nodes in the array node.
     *
     * @return an array containing the double values of the nodes in the array node
     */
    public double[] getDoubleArray() {
        int length = length();
        double[] array = new double[length];
        for (int i = 0; i < length; i++) {
            final Token token = tokens.get(i + 1);
            array[i] = source.getDouble(token.startIndex, token.endIndex);
        }
        return array;
    }

    /**
     * Returns an array containing the float values of the nodes in the array node.
     *
     * @return an array containing the float values of the nodes in the array node
     */
    public float[] getFloatArray() {
        int length = length();
        float[] array = new float[length];
        for (int i = 0; i < length; i++) {
            final Token token = tokens.get(i + 1);
            array[i] = source.getFloat(token.startIndex, token.endIndex);
        }
        return array;
    }

    /**
     * Returns an array containing the BigDecimal values of the nodes in the array node.
     *
     * @return an array containing the BigDecimal values of the nodes in the array node
     */
    public BigDecimal[] getBigDecimalArray() {
        int length = length();
        BigDecimal[] array = new BigDecimal[length];
        for (int i = 0; i < length; i++) {
            final Token token = tokens.get(i + 1);
            array[i] = source.getBigDecimal(token.startIndex, token.endIndex);
        }
        return array;
    }

    /**
     * Returns an array containing the BigInteger values of the nodes in the array node.
     *
     * @return an array containing the BigInteger values of the nodes in the array node
     */
    public BigInteger[] getBigIntegerArray() {
        int length = length();
        BigInteger[] array = new BigInteger[length];
        for (int i = 0; i < length; i++) {
            final Token token = tokens.get(i + 1);
            array[i] = source.getBigDecimal(token.startIndex, token.endIndex).toBigInteger();
        }
        return array;
    }

    /**
     * Returns an array containing the int values of the nodes in the array node.
     *
     * @return an array containing the int values of the nodes in the array node
     */
    public int[] getIntArray() {
        int length = length();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            final Token token = tokens.get(i + 1);
            array[i] = source.getInt(token.startIndex, token.endIndex);
        }
        return array;
    }

    /**
     * Returns an array containing the long values of the nodes in the array node.
     *
     * @return an array containing the long values of the nodes in the array node
     */
    public long[] getLongArray() {
        int length = length();
        long[] array = new long[length];
        for (int i = 0; i < length; i++) {
            final Token token = tokens.get(i + 1);
            array[i] = source.getLong(token.startIndex, token.endIndex);
        }
        return array;
    }

    /**
     * Returns the null node at the specified index in the array node.
     *
     * @param index the index of the null node to retrieve
     * @return the null node at the specified index
     */
    public int getInt(int index) {
        return getNumberNode(index).intValue();
    }

    /**
     * Returns the null node at the specified index in the array node.
     *
     * @param index the index of the null node to retrieve
     * @return the null node at the specified index
     */
    public NullNode getNullNode(int index) {
        return (NullNode) getNodeAt(index);
    }

    /**
     * Returns the float value at the specified index in the array.
     *
     * @param index the index of the float value
     * @return the float value at the specified index
     */
    public float getFloat(int index) {
        return getNumberNode(index).floatValue();
    }

    /**
     * Returns the NumberNode at the specified index in the array.
     *
     * @param index the index of the NumberNode
     * @return the NumberNode at the specified index
     */
    public NumberNode getNumberNode(int index) {
        return (NumberNode) getNodeAt(index);
    }

    /**
     * Returns the BigDecimal value at the specified index in the array.
     *
     * @param index the index of the BigDecimal value
     * @return the BigDecimal value at the specified index
     */
    public BigDecimal getBigDecimal(int index) {
        return getNumberNode(index).bigDecimalValue();
    }

    /**
     * Returns the BigInteger value at the specified index in the array.
     *
     * @param index the index of the BigInteger value
     * @return the BigInteger value at the specified index
     */
    public BigInteger getBigInteger(int index) {
        return getNumberNode(index).bigIntegerValue();
    }

    /**
     * Returns the StringNode at the specified index in the array.
     *
     * @param index the index of the StringNode
     * @return the StringNode at the specified index
     */
    public StringNode getStringNode(int index) {
        return (StringNode) getNodeAt(index);
    }

    /**
     * Returns the string value at the specified index in the array.
     *
     * @param index the index of the string value
     * @return the string value at the specified index
     */
    public String getString(int index) {
        return getStringNode(index).toString();
    }

    /**
     * Returns the ObjectNode at the specified index in the array.
     *
     * @param index the index of the ObjectNode
     * @return the ObjectNode at the specified index
     */
    public ObjectNode getObjectNode(int index) {
        return (ObjectNode) getNodeAt(index);
    }

    /**
     * Returns the ArrayNode at the specified index in the array.
     *
     * @param index the index of the ArrayNode
     * @return the ArrayNode at the specified index
     */
    public ArrayNode getArray(int index) {
        return (ArrayNode) getNodeAt(index);
    }

    /**
     * Returns the BooleanNode at the specified index in the array.
     *
     * @param index the index of the BooleanNode
     * @return the BooleanNode at the specified index
     */
    public BooleanNode getBooleanNode(int index) {
        return (BooleanNode) getNodeAt(index);
    }

    /**
     * Returns the boolean value at the specified index in the array.
     *
     * @param index the index of the boolean value
     * @return the boolean value at the specified index
     */
    public boolean getBoolean(int index) {
        return getBooleanNode(index).booleanValue();
    }

    /**
     * Returns the length of the array.
     *
     * @return the length of the array
     */
    public int length() {
        return elements().length;
    }

    /**
     * Returns the type of the node, which is NodeType.ARRAY.
     *
     * @return the type of the node
     */
    @Override
    public NodeType type() {
        return NodeType.ARRAY;
    }

    /**
     * Returns the list of tokens representing the array.
     *
     * @return the list of tokens representing the array
     */
    @Override
    public List<Token> tokens() {
        return tokens;
    }

    /**
     * Returns the root token of the array.
     *
     * @return the root token of the array
     */
    @Override
    public Token rootElementToken() {
        return rootToken;
    }

    /**
     * Returns the character source of the array.
     *
     * @return the character source of the array
     */
    @Override
    public CharSource charSource() {
        return source;
    }

    /**
     * Returns the node at the specified index in the array.
     * If the node at the index is of type NodeType.NULL, returns null.
     *
     * @param index the index of the node
     * @return the node at the specified index, or null if it is of type NodeType.NULL
     */
    @Override
    public Node get(int index) {
        final Node node = getNodeAt(index);
        return node.type() == NodeType.NULL ? null : node;
    }

    /**
     * Checks if this ArrayNode is equal to the specified object.
     * <p>
     * Two ArrayNodes are considered equal if they have the same tokens.
     *
     * @param o the object to compare
     * @return true if the ArrayNodes are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayNode)) return false;

        final ArrayNode other = (ArrayNode) o;

        if (this.tokens.size() != other.tokens.size()) {
            return false;
        }

        for (int index = 0; index < this.tokens.size(); index++) {
            Token thisValue = this.tokens.get(index);
            Token otherValue = other.tokens.get(index);
            if (otherValue == null && thisValue == null) continue;
            String thisStr = thisValue.asString(this.source);
            String otherStr = otherValue.asString(other.source);
            if (!thisStr.equals(otherStr)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the hash code value for the ArrayNode.
     * The hash code is computed based on the tokens of the ArrayNode.
     *
     * @return the hash code value for the ArrayNode
     */
    @Override
    public int hashCode() {
        if (hashCodeSet) {
            return hashCode;
        }
        hashCode = Objects.hash(tokens.stream().map(tok -> tok.asString(this.source)).collect(Collectors.toList()));
        hashCodeSet = true;
        return hashCode;
    }

    /**
     * Returns the size of the array, which is the number of elements it contains.
     *
     * @return the size of the array
     */
    @Override
    public int size() {
        return childrenTokens().size();
    }

    /**
     * Returns a string representation of the ArrayNode.
     *
     * @return a string representation of the ArrayNode
     */
    @Override
    public String toString() {
        return this.originalString();
    }

    /**
     * Maps the ObjectNode elements in the array to a new list of type R
     * using the provided mapper function.
     *
     * @param mapper the mapper function to apply to each ObjectNode
     * @param <R>    the type of the resulting list elements
     * @return a new list of type R resulting from the mapping
     */
    public <R> List<R> mapObjectNode(Function<ObjectNode, ? extends R> mapper) {
        return map(node -> mapper.apply(node.asCollection().asObject()));
    }

    /**
     * Maps the elements in the array to a new list of type R using the provided mapper function.
     *
     * @param mapper the mapper function to apply to each element
     * @param <R>    the type of the resulting list elements
     * @return a new list of type R resulting from the mapping
     */
    public <R> List<R> map(Function<Node, ? extends R> mapper) {
        List<R> list = new ArrayList<>(this.size());
        Node[] elements = elements();
        for (int i = 0; i < elements.length; i++) {
            Node element = elements[i];
            if (element == null) {
                element = getNodeAt(i);
                elements[i] = element;
            }
            list.add(mapper.apply(element));
        }
        return list;
    }

    /**
     * Finds the first ObjectNode in the array that matches the specified predicate.
     *
     * @param predicate the predicate to apply to each ObjectNode
     * @return an Optional containing the first matching ObjectNode, or an empty Optional if no match is found
     */
    public Optional<ObjectNode> findObjectNode(Predicate<ObjectNode> predicate) {
        final Node[] elements = elements();
        ObjectNode node = null;
        for (int i = 0; i < elements.length; i++) {
            Node element = elements[i];
            if (element == null) {
                element = getNodeAt(i);
            }
            if (element.type() == NodeType.OBJECT) {
                ObjectNode objectNode = element.asCollection().asObject();
                if (predicate.test(objectNode)) {
                    node = objectNode;
                    break;
                }
            }
        }
        return Optional.ofNullable(node);
    }

    /**
     * Finds the first Node in the array that matches the specified predicate.
     *
     * @param predicate the predicate to apply to each Node
     * @return an Optional containing the first matching Node, or an empty Optional if no match is found
     */
    public Optional<Node> find(Predicate<Node> predicate) {
        Node[] elements = elements();
        Node node = null;
        for (int i = 0; i < elements.length; i++) {
            Node element = elements[i];
            if (element == null) {
                element = getNodeAt(i);
            }
            if (predicate.test(element)) {
                node = element;
                break;
            }
        }
        return Optional.ofNullable(node);
    }

    /**
     * Filters the ObjectNode elements in the array based on the specified predicate.
     *
     * @param predicate the predicate to apply to each ObjectNode
     * @return a new list containing the filtered ObjectNodes
     */
    public List<ObjectNode> filterObjects(Predicate<ObjectNode> predicate) {
        Node[] elements = elements();
        final int length = elements.length;
        final List<ObjectNode> arrayList = new ArrayList<>(length / 2);
        for (int i = 0; i < length; i++) {
            Node element = elements[i];
            if (element == null) {
                element = getNodeAt(i);
            }
            if (element.type() == NodeType.OBJECT) {
                ObjectNode objectNode = element.asCollection().asObject();
                if (predicate.test(objectNode)) {
                    arrayList.add(objectNode);
                }
            }
        }
        return arrayList;
    }

    /**
     * Filters the elements in the array based on the specified predicate.
     *
     * @param predicate the predicate to apply to each Node
     * @return a new list containing the filtered Nodes
     */
    public List<Node> filter(Predicate<Node> predicate) {
        Node[] elements = elements();
        final int length = elements.length;
        final List<Node> arrayList = new ArrayList<>(length / 2);
        for (int i = 0; i < length; i++) {
            Node element = elements[i];
            if (element == null) {
                element = getNodeAt(i);
            }
            if (predicate.test(element)) {
                arrayList.add(element);
            }
        }
        return arrayList;
    }


}

```

###### CollectionNode.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node;

import io.nats.jparse.token.Token;

import java.util.List;
import java.util.Optional;

/**
 * The CollectionNode interface represents a collection node in a tree structure.
 * <p>
 * It extends the Node interface.
 */
public interface CollectionNode extends Node {

    /**
     * Returns whether the collection node is a scalar node.
     * In a collection node, this method always returns false.
     *
     * @return false
     */
    @Override
    default boolean isScalar() {
        return false;
    }

    /**
     * Returns whether the collection node is a collection node.
     * In a collection node, this method always returns true.
     *
     * @return true
     */
    @Override
    default boolean isCollection() {
        return true;
    }

    /**
     * Returns the node associated with the specified key.
     *
     * @param key the key to retrieve the associated node
     * @return the node associated with the specified key
     */
    Node getNode(Object key);

    /**
     * Returns an optional that contains the node associated with the specified key,
     * or an empty optional if no node is associated with the key.
     *
     * @param key the key to retrieve the associated node
     * @return an optional that contains the node associated with the specified key,
     * or an empty optional if no node is associated with the key
     */
    default Optional<Node> lookupNode(Object key) {
        return Optional.ofNullable(getNode(key));
    }

    /**
     * Returns a list of lists of tokens representing the children of the collection node.
     *
     * @return a list of lists of tokens representing the children of the collection node
     */
    List<List<Token>> childrenTokens();

    /**
     * Returns the collection node as an array node.
     * This method should be implemented by the ArrayNode class.
     *
     * @return the collection node as an array node
     */
    default ArrayNode asArray() {
        return (ArrayNode) this;
    }

    /**
     * Returns the collection node as an object node.
     * This method should be implemented by the ObjectNode class.
     *
     * @return the collection node as an object node
     */
    default ObjectNode asObject() {
        return (ObjectNode) this;
    }
}

```

###### NodeType.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node;


import io.nats.jparse.token.TokenTypes;

/**
 * This enum represents the different types of nodes in the JParse library.
 * <p>
 * Each node type corresponds to a specific token type.
 *
 * <p>The enum values include ROOT, OBJECT, ARRAY, INT, FLOAT, STRING, BOOLEAN, NULL,
 * PATH_KEY, PATH_INDEX, PATH, and OTHER.
 *
 * <p>The tokenTypeToElement method allows converting a token type to its corresponding NodeType.
 *
 * @see io.nats.jparse.token.TokenTypes
 */
public enum NodeType implements TokenTypes {

    /**

     The root node type.
     */
    ROOT(-1),
    /**

     The object node type.
     */
    OBJECT(OBJECT_TOKEN),
    /**

     The array node type.
     */
    ARRAY(ARRAY_TOKEN),
    /**

     The integer node type.
     */
    INT(INT_TOKEN),
    /**

     The float node type.
     */
    FLOAT(FLOAT_TOKEN),
    /**

     The string node type.
     */
    STRING(STRING_TOKEN),
    /**

     The boolean node type.
     */
    BOOLEAN(BOOLEAN_TOKEN),
    /**

     The null node type.
     */
    NULL(NULL_TOKEN),
    /**

     The path key node type.
     */
    PATH_KEY(PATH_KEY_TOKEN),
    /**

     The path index node type.
     */
    PATH_INDEX(PATH_INDEX_TOKEN),
    /**

     The path node type.
     */
    PATH(-1),
    /**

     An other or unrecognized node type.
     */
    OTHER(-2);
    private final int tokenType;

    NodeType(int tokenType) {
        this.tokenType = tokenType;
    }

    /**

     Converts a token type to its corresponding NodeType.
     @param tokenType the token type to convert
     @return the corresponding NodeType
     @throws IllegalStateException if the token type is invalid
     */
    public static NodeType tokenTypeToElement(final int tokenType) {
        switch (tokenType) {
            case OBJECT_TOKEN:
                return NodeType.OBJECT;
            case ARRAY_TOKEN:
                return NodeType.ARRAY;
            case INT_TOKEN:
                return NodeType.INT;
            case FLOAT_TOKEN:
                return NodeType.FLOAT;
            case STRING_TOKEN:
                return NodeType.STRING;
            case BOOLEAN_TOKEN:
                return NodeType.BOOLEAN;
            case NULL_TOKEN:
                return NodeType.NULL;
            case PATH_KEY_TOKEN:
                return NodeType.PATH_KEY;
            case PATH_INDEX_TOKEN:
                return NodeType.PATH_INDEX;
            default:
                throw new IllegalStateException(String.valueOf(tokenType));
        }
    }
}

```

###### BooleanNode.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node;

import io.nats.jparse.source.CharSource;
import io.nats.jparse.token.Token;

import java.util.Collections;
import java.util.List;

/**
 * The BooleanNode class represents a boolean value node in a tree structure.
 * <p>
 * It implements the ScalarNode interface.
 */
public class BooleanNode implements ScalarNode {

    private final Token token;
    private final CharSource source;
    private final boolean value;

    /**
     * Constructs a BooleanNode with the specified token and source.
     *
     * @param token  the token representing the boolean value
     * @param source the character source containing the boolean value
     */
    public BooleanNode(final Token token, final CharSource source) {
        this.token = token;
        this.source = source;
        this.value = source.getChartAt(token.startIndex) == 't';
    }

    /**
     * Returns the element type of the boolean node.
     *
     * @return the element type of the boolean node
     */
    @Override
    public NodeType type() {
        return NodeType.BOOLEAN;
    }

    /**
     * Returns a list containing the token of the boolean node.
     *
     * @return a list containing the token of the boolean node
     */
    @Override
    public List<Token> tokens() {
        return Collections.singletonList(token);
    }

    /**
     * Returns the root element token of the boolean node.
     *
     * @return the root element token of the boolean node
     */
    @Override
    public Token rootElementToken() {
        return token;
    }

    /**
     * Returns the character source associated with the boolean node.
     *
     * @return the character source associated with the boolean node
     */
    @Override
    public CharSource charSource() {
        return source;
    }

    /**
     * Returns the boolean value of the boolean node.
     *
     * @return the boolean value of the boolean node
     */
    public boolean booleanValue() {
        return value;
    }

    /**
     * Returns the length of the boolean node.
     *
     * @return the length of the boolean node
     */
    @Override
    public int length() {
        return value ? 4 : 5;
    }

    /**
     * Returns the value of the boolean node.
     *
     * @return the value of the boolean node
     */
    @Override
    public Object value() {
        return booleanValue();
    }

    /**
     * Returns the character at the specified index in the boolean node.
     *
     * @param index the index of the character to retrieve
     * @return the character at the specified index
     * @throws IllegalStateException if the index is out of range
     */
    @Override
    public char charAt(int index) {
        if (value) {
            switch (index) {
                case 0:
                    return 't';
                case 1:
                    return 'r';
                case 2:
                    return 'u';
                case 3:
                    return 'e';
                default:
                    throw new IllegalStateException();
            }
        } else {
            switch (index) {
                case 0:
                    return 'f';
                case 1:
                    return 'a';
                case 2:
                    return 'l';
                case 3:
                    return 's';
                case 4:
                    return 'e';
                default:
                    throw new IllegalStateException();
            }
        }
    }

    /**
     * Returns the string representation of the boolean node.
     *
     * @return the string representation of the boolean node
     */
    @Override
    public String toString() {
        return value ? "true" : "false";
    }

    /**
     * Checks if the boolean node is equal to the specified object.
     * <p>
     * The boolean node is considered equal to another object if the other object is also a BooleanNode
     * <p>
     * and has the same boolean value.
     *
     * @param o the object to compare with the boolean node
     * @return true if the boolean node is equal to the specified object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o instanceof BooleanNode) {
            BooleanNode that = (BooleanNode) o;
            return value == that.value;
        } else if (o instanceof Boolean) {
            Boolean that = (Boolean) o;
            return value == that;
        }
        return false;
    }

    /**
     * Returns the hash code value for the boolean node.
     *
     * @return the hash code value for the boolean node
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}

```

###### support/

####### CharArrayUtils.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node.support;

/**
 * Utility class for working with character arrays.
 */
public class CharArrayUtils {

    private CharArrayUtils(){}
    /**
     * Mapping of escape control characters.
     */
    static final char[] controlMap = new char[255];
    /**
     * Mapping of hex values.
     */
    static final int[] hexValueMap = new int[255];
    /**
     * Escape character code.
     */
    static final int ESCAPE = '\\';
    /**
     * Value of 10s place in hexadecimal.
     */
    private final static int HEX_10s = 16;
    /**
     * Value of 100s place in hexadecimal.
     */
    private final static int HEX_100s = 16 * 16;
    /**
     * Value of 1000s place in hexadecimal.
     */
    private final static int HEX_1000s = 16 * 16 * 16;

    /**

     Initializes the controlMap and hexValueMap arrays with mappings for escape control characters
     and hex values, respectively.
     */
    static {
        controlMap['n'] = '\n';
        controlMap['b'] = '\b';
        controlMap['/'] = '/';
        controlMap['f'] = '\f';
        controlMap['r'] = '\r';
        controlMap['t'] = '\t';
        controlMap['\\'] = '\\';
        controlMap['"'] = '"';
    }

    static {
        hexValueMap['0'] = 0;
        hexValueMap['1'] = 1;
        hexValueMap['2'] = 2;
        hexValueMap['3'] = 3;
        hexValueMap['4'] = 4;
        hexValueMap['5'] = 5;
        hexValueMap['6'] = 6;
        hexValueMap['7'] = 7;
        hexValueMap['8'] = 8;
        hexValueMap['9'] = 9;
        hexValueMap['a'] = 10;
        hexValueMap['b'] = 11;
        hexValueMap['c'] = 12;
        hexValueMap['d'] = 13;
        hexValueMap['e'] = 14;
        hexValueMap['f'] = 15;
        hexValueMap['A'] = 10;
        hexValueMap['B'] = 11;
        hexValueMap['C'] = 12;
        hexValueMap['D'] = 13;
        hexValueMap['E'] = 14;
        hexValueMap['F'] = 15;
    }

    /**
     * Decodes a JSON string from the specified character array within the specified range.
     *
     * @param chars      the character array containing the JSON string
     * @param startIndex the start index of the JSON string within the character array
     * @param endIndex   the end index of the JSON string within the character array (exclusive)
     * @return the decoded JSON string
     */
    public static String decodeJsonString(char[] chars, int startIndex, int endIndex) {
        int length = endIndex - startIndex;
        char[] builder = new char[calculateLengthAfterEncoding(chars, startIndex, endIndex, length)];
        char c;
        int index = startIndex;
        int idx = 0;

        while (true) {
            c = chars[index];
            if (c == '\\' && index < (endIndex - 1)) {
                index++;
                c = chars[index];
                if (c != 'u') {
                    builder[idx] = controlMap[c];
                    idx++;
                } else {

                    if (index + 4 < endIndex) {
                        char unicode = getUnicode(chars, index);
                        builder[idx] = unicode;
                        index += 4;
                        idx++;
                    }
                }

            } else {
                builder[idx] = c;
                idx++;
            }
            if (index >= (endIndex - 1)) {
                break;
            }
            index++;
        }
        return new String(builder);

    }

    /**
     * Retrieves the Unicode character from the specified character array at the given index.
     *
     * @param chars the character array
     * @param index the index of the Unicode character
     * @return the Unicode character
     */
    private static char getUnicode(char[] chars, int index) {
        int d4 = hexValueMap[chars[index + 1]];
        int d3 = hexValueMap[chars[index + 2]];
        int d2 = hexValueMap[chars[index + 3]];
        int d1 = hexValueMap[chars[index + 4]];
        return (char) (d1 + (d2 * HEX_10s) + (d3 * HEX_100s) + (d4 * HEX_1000s));
    }

    /**
     * Calculates the length of the character array after encoding, excluding escape control characters.
     *
     * @param chars      the character array
     * @param startIndex the start index of the range to calculate
     * @param endIndex   the end index of the range to calculate
     * @param length     the original length of the range
     * @return the length of the character array after encoding
     */
    private static int calculateLengthAfterEncoding(char[] chars, int startIndex, int endIndex, int length) {
        char c;
        int index = startIndex;
        int controlCharCount = length;

        while (true) {
            c = chars[index];
            if (c == '\\' && index < (endIndex - 1)) {
                index++;
                c = chars[index];
                if (c != 'u') {
                    controlCharCount -= 1;
                } else {

                    if (index + 4 < endIndex) {
                        controlCharCount -= 5;
                        index += 4;
                    }
                }

            }
            if (index >= (endIndex - 1)) {
                break;
            }
            index++;
        }
        return controlCharCount;
    }


    /**
     * Checks if the specified character array contains any escape characters.
     *
     * @param array      the character array to check
     * @param startIndex the start index of the range to check
     * @param endIndex   the end index of the range to check
     * @return true if the array contains an escape character, false otherwise
     */
    public static boolean hasEscapeChar(char[] array, int startIndex, int endIndex) {
        char currentChar;
        for (int index = startIndex; index < endIndex; index++) {
            currentChar = array[index];
            if (currentChar == ESCAPE) {
                return true;
            }

        }
        return false;
    }
}

```

####### TokenSubList.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node.support;

import io.nats.jparse.token.Token;

import java.util.AbstractList;
import java.util.List;

/**
 * A sublist implementation for storing a portion of tokens from a TokenList.
 * <p>
 * The TokenSubList class is a sublist implementation that represents a portion of tokens from a TokenList.
 * It provides methods for accessing tokens within the sublist, getting the size of the sublist, creating sub lists,
 * converting the sublist to an array, and counting the number of children tokens within a specified range relative
 * to a root token.
 * </p>
 */
public class TokenSubList extends AbstractList<Token> {

    private final int size;
    private final Token[] tokens;
    private final int offset;
    private final int endIndex;

    /**
     * Constructs a TokenSubList with the given tokens, offset, and endIndex.
     *
     * @param tokens   the array of tokens
     * @param offset   the starting index of the sublist (inclusive)
     * @param endIndex the ending index of the sublist (exclusive)
     */
    public TokenSubList(Token[] tokens, int offset, int endIndex) {
        size = endIndex - offset;
        this.tokens = tokens;
        this.offset = offset;
        this.endIndex = endIndex;
    }

    /**
     * Returns the token at the specified index in this sublist.
     *
     * @param index the index of the token to return
     * @return the token at the specified index
     */
    @Override
    public Token get(int index) {
        return tokens[offset + index];
    }

    /**
     * Returns the size of this sublist.
     *
     * @return the size of the sublist
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns a view of the portion of this sublist between the specified startIndex (inclusive) and endIndex (exclusive).
     *
     * @param startIndex the start index of the sublist view (inclusive)
     * @param endIndex   the end index of the sublist view (exclusive)
     * @return the sublist view
     */
    @Override
    public List<Token> subList(int startIndex, int endIndex) {
        return new TokenSubList(tokens, this.offset + startIndex, this.offset + endIndex);
    }

    /**
     * Returns an array containing all of the tokens in this sublist.
     *
     * @return an array of tokens
     */
    public Token[] toArray() {
        Token[] array = new Token[size];
        System.arraycopy(tokens, offset, array, 0, size);
        return array;
    }

    /**
     * Counts the number of children tokens within the specified range, relative to a root token.
     *
     * @param from      the starting index to count children from
     * @param rootToken the root token to compare against
     * @return the number of children tokens
     */
    public int countChildren(final int from, final Token rootToken) {
        int idx = from;
        int count = 0;
        final Token[] tokens = this.tokens;
        final int length = this.size;
        final int offset = this.offset;
        final int rootTokenStart = rootToken.startIndex;
        final int rootTokenEnd = rootToken.endIndex;

        for (; idx < length; idx++) {
            Token token = tokens[idx + offset];

            if (token.startIndex >= rootTokenStart && token.endIndex <= rootTokenEnd) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }
}

```

####### TokenList.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node.support;

import io.nats.jparse.token.Token;

import java.util.AbstractList;
import java.util.List;

/**
 * A list implementation for storing tokens.
 * <p>
 * The TokenList class is an implementation of a list that stores tokens. It provides methods for adding tokens,
 * accessing tokens by index, clearing the list, creating sub lists, and more. The class also includes methods
 * for managing placeholder tokens and creating compact clones of the list.
 * </p>
 */
public class TokenList extends AbstractList<Token> {

    private Token[] tokens;
    private int index = 0;

    /**
     * Constructs an empty TokenList with an initial capacity of 32.
     */
    public TokenList() {
        this.tokens = new Token[32];
    }

    /**
     * Constructs a TokenList with the given array of tokens.
     *
     * @param tokens the array of tokens to initialize the TokenList
     */
    public TokenList(Token[] tokens) {
        index = tokens.length;
        this.tokens = tokens;
    }

    /**
     * Removes all tokens from the list.
     */
    @Override
    public void clear() {
        index = 0;
    }

    /**
     * Adds a token to the list.
     *
     * @param token the token to add
     * @return true (as specified by Collection.add)
     */
    @Override
    public final boolean add(Token token) {
        final int length = tokens.length;
        if (index >= length) {
            final Token[] newTokens = new Token[length * 2];
            System.arraycopy(tokens, 0, newTokens, 0, length);
            tokens = newTokens;
        }
        tokens[index] = token;
        index++;
        return true;
    }

    /**
     * Returns a view of the portion of this list between the specified fromIndex (inclusive) and toIndex (exclusive).
     *
     * @param from the start index of the sublist (inclusive)
     * @param to   the end index of the sublist (exclusive)
     * @return the sublist view of the TokenList
     */
    @Override
    public final List<Token> subList(final int from, final int to) {
        return new TokenSubList(tokens, from, to);
    }

    /**
     * Returns the current index.
     *
     * @return the current index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Replaces the token at the specified index with the specified element.
     *
     * @param index   the index of the token to replace
     * @param element the new token to be stored at the specified position
     * @return the token previously at the specified position
     */
    @Override
    public final Token set(final int index, final Token element) {
        tokens[index] = element;
        return null;
    }

    /**
     * Returns the token at the specified index.
     *
     * @param index the index of the token to return
     * @return the token at the specified index
     */
    @Override
    public Token get(int index) {
        return tokens[index];
    }

    /**
     * Returns the number of tokens in the list.
     *
     * @return the number of tokens in the list
     */
    @Override
    public int size() {
        return index;
    }

    /**
     * Returns the array of tokens in the list.
     *
     * @return the array of tokens in the list
     */
    public Token[] getTokens() {
        return tokens;
    }

    /**
     * Adds a placeholder token to the list.
     */
    public void placeHolder() {
        final int length = tokens.length;
        if (index >= length) {
            final Token[] newTokens = new Token[length * 2];
            System.arraycopy(tokens, 0, newTokens, 0, length);
            tokens = newTokens;
        }
        index++;
    }

    /**
     * Creates a compact clone of the TokenList with only the current tokens.
     *
     * @return a compact clone of the TokenList
     */
    public TokenList compactClone() {
        final int length = index;
        final Token[] newTokens = new Token[index];
        System.arraycopy(tokens, 0, newTokens, 0, length);
        return new TokenList(newTokens);
    }

    /**
     * Undoes the placeholder by decrementing the index.
     */
    public void undoPlaceholder() {
        index--;
    }
}

```

####### CharSequenceUtils.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node.support;

/**
 * Utility class for working with CharSequence objects.
 */
public class CharSequenceUtils {

    private CharSequenceUtils() {}

    /**
     * Compares two CharSequence objects for equality.
     *
     * @param cs1 the first CharSequence
     * @param cs2 the second CharSequence
     * @return true if the CharSequences are equal, false otherwise
     */
    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        if (cs1.length() != cs2.length()) {
            return false;
        }
        final int len = cs1.length();
        for (int i = 0; i < len; i++) {
            char a = cs1.charAt(i);
            char b = cs2.charAt(i);
            if (a != b) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates the hash code of a CharSequence.
     *
     * @param cs the CharSequence
     * @return the hash code value for the CharSequence
     */
    public static int hashCode(final CharSequence cs) {
        int h = 0;
        for (int index = 0; index < cs.length(); index++) {
            char v = cs.charAt(index);
            h = 31 * h + v;
        }
        return h;
    }
}

```

####### NodeUtils.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node.support;

import io.nats.jparse.node.*;
import io.nats.jparse.path.IndexPathNode;
import io.nats.jparse.path.KeyPathNode;
import io.nats.jparse.source.CharSource;
import io.nats.jparse.token.Token;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.nats.jparse.token.TokenTypes.ARRAY_ITEM_TOKEN;

/**
 * Utility class for working with Node objects.
 */
public class NodeUtils {

    private NodeUtils() {}

    /**
     * Retrieves the children tokens of a given TokenSubList.
     *
     * @param tokens the TokenSubList representing the root element and its children
     * @return the List of children tokens
     */
    public static List<List<Token>> getChildrenTokens(final TokenSubList tokens) {
        final Token root = tokens.get(0);
        final List<List<Token>> childrenTokens = new ArrayList<>(16);

        for (int index = 1; index < tokens.size(); index++) {
            Token token = tokens.get(index);

            if (token.startIndex > root.endIndex) {
                break;
            }

            if (token.type <= ARRAY_ITEM_TOKEN) {
                int childCount = tokens.countChildren(index, token);
                int endIndex = index + childCount;
                childrenTokens.add(tokens.subList(index, endIndex));
                index = endIndex - 1;
            } else {
                childrenTokens.add(Collections.singletonList(token));
            }
        }

        return childrenTokens;
    }

    /**
     * Creates a Node object based on the given tokens and source.
     *
     * @param tokens                  the List of tokens representing the node
     * @param source                  the CharSource providing the character data
     * @param objectsKeysCanBeEncoded whether object keys can be encoded
     * @return the created Node object
     * @throws IllegalStateException if the NodeType is invalid
     */
    public static Node createNode(final List<Token> tokens, final CharSource source, boolean objectsKeysCanBeEncoded) {
        final NodeType nodeType = NodeType.tokenTypeToElement(tokens.get(0).type);

        switch (nodeType) {
            case ARRAY:
                return new ArrayNode((TokenSubList) tokens, source, objectsKeysCanBeEncoded);
            case INT:
                return new NumberNode(tokens.get(0), source, NodeType.INT);
            case FLOAT:
                return new NumberNode(tokens.get(0), source, NodeType.FLOAT);
            case OBJECT:
                return new ObjectNode((TokenSubList) tokens, source, objectsKeysCanBeEncoded);
            case STRING:
                return new StringNode(tokens.get(0), source);
            case BOOLEAN:
                return new BooleanNode(tokens.get(0), source);
            case NULL:
                return new NullNode(tokens.get(0), source);
            case PATH_INDEX:
                return new IndexPathNode(tokens.get(0), source);
            case PATH_KEY:
                return new KeyPathNode(tokens.get(0), source);
            default:
                throw new IllegalStateException();
        }
    }

    /**
     * Creates a Node object for an object representation based on the given tokens and source.
     *
     * @param theTokens               the List of tokens representing the object
     * @param source                  the CharSource providing the character data
     * @param objectsKeysCanBeEncoded whether object keys can be encoded
     * @return the created Node object
     * @throws IllegalStateException if the NodeType is invalid
     */
    public static Node createNodeForObject(final List<Token> theTokens, final CharSource source, boolean objectsKeysCanBeEncoded) {
        final Token rootToken = theTokens.get(1);
        final List<Token> tokens = theTokens.subList(1, theTokens.size());
        final NodeType nodeType = NodeType.tokenTypeToElement(rootToken.type);

        switch (nodeType) {
            case ARRAY:
                return new ArrayNode((TokenSubList) tokens, source, objectsKeysCanBeEncoded);
            case INT:
                return new NumberNode(tokens.get(0), source, NodeType.INT);
            case FLOAT:
                return new NumberNode(tokens.get(0), source, NodeType.FLOAT);
            case OBJECT:
                return new ObjectNode((TokenSubList) tokens, source, objectsKeysCanBeEncoded);
            case STRING:
                return new StringNode(tokens.get(0), source);
            case BOOLEAN:
                return new BooleanNode(tokens.get(0), source);
            case NULL:
                return new NullNode(tokens.get(0), source);
            default:
                throw new IllegalStateException();
        }
    }
}

```

####### ParseConstants.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node.support;

/**
 * The ParseConstants class provides a Java interface that defines constants used for parsing JSON strings.
 * It includes a number of integer constants, such as tokens for object and array delimiters, as well as
 * string constants for the minimum and maximum values of integers and longs. The interface also includes
 * a number of character constants, such as those for whitespace and various characters used in the JSON format.
 */
public interface ParseConstants {
    /**
     * The maximum nest level for parsing operations.
     */
    int NEST_LEVEL = 2_000;

    /**
     * The ASCII control character for end of text.
     */
    int ETX = 3;

    /**
     * The ASCII value for the start of a true boolean.
     */
    int TRUE_BOOLEAN_START = 't';

    /**
     * The ASCII value for the start of a null value.
     */
    int NULL_START = 'n';

    /**
     * The ASCII value for the start of a false boolean.
     */
    int FALSE_BOOLEAN_START = 'f';
    /**
     * The ASCII value for the start of an object.
     */
    int OBJECT_START_TOKEN = '{';

    /**
     * The ASCII value for the end of an object.
     */
    int OBJECT_END_TOKEN = '}';

    /**
     * The ASCII value for the start of an array.
     */
    int ARRAY_START_TOKEN = '[';

    /**
     * The ASCII value for the end of an array.
     */
    int ARRAY_END_TOKEN = ']';

    /**
     * The ASCII value for separating attributes.
     */
    int ATTRIBUTE_SEP = ':';

    /**
     * The ASCII value for separating array elements.
     */
    int ARRAY_SEP = ',';

    /**
     * The ASCII value for separating object attributes.
     */
    int OBJECT_ATTRIBUTE_SEP = ',';

    /**
     * The ASCII value for the start of an index bracket.
     */
    int INDEX_BRACKET_START_TOKEN = ARRAY_START_TOKEN;

    /**
     * The ASCII value for the end of an index bracket.
     */
    int INDEX_BRACKET_END_TOKEN = ARRAY_END_TOKEN;

    /**
     * The ASCII value for the start of a string.
     */
    int STRING_START_TOKEN = '"';

    /**
     * The ASCII value for the end of a string.
     */
    int STRING_END_TOKEN = '"';

    /**
     * The ASCII value for a new line whitespace character.
     */
    int NEW_LINE_WS = '\n';

    /**
     * The ASCII value for a tab whitespace character.
     */
    int TAB_WS = '\t';

    /**
     * The ASCII value for a carriage return whitespace character.
     */
    int CARRIAGE_RETURN_WS = '\r';

    /**
     * The ASCII value for a space whitespace character.
     */
    int SPACE_WS = ' ';

    /**
     * The ASCII value for the Delete character.
     */
    int DEL = 127;

    /**
     * The ASCII value for the control escape token.
     */
    int CONTROL_ESCAPE_TOKEN = '\\';

    /**
     * The ASCII value for the numeral '0'.
     */
    int NUM_0 = '0';

    /**
     * The ASCII value for the numeral '1'.
     */
    int NUM_1 = '1';

    /**
     * The ASCII value for the numeral '2'.
     */
    int NUM_2 = '2';

    /**
     * The ASCII value for the numeral '3'.
     */
    int NUM_3 = '3';

    /**
     * The ASCII value for the numeral '4'.
     */
    int NUM_4 = '4';

    /**
     * The ASCII value for the numeral '5'.
     */
    int NUM_5 = '5';

    /**
     * The ASCII value for the numeral '6'.
     */
    int NUM_6 = '6';

    /**
     * The ASCII value for the numeral '7'.
     */
    int NUM_7 = '7';

    /**
     * The ASCII value for the numeral '8'.
     */
    int NUM_8 = '8';

    /**
     * The ASCII value for the numeral '9'.
     */
    int NUM_9 = '9';

    /**
     * The ASCII value for a decimal point, used in decimal numbers.
     */
    int DECIMAL_POINT = '.';

    /**
     * The ASCII value for the minus symbol, used in negative numbers and subtraction operations.
     */
    int MINUS = '-';

    /**
     * The ASCII value for the plus symbol, used in positive numbers and addition operations.
     */
    int PLUS = '+';

    /**
     * The ASCII value for a lowercase 'e', often used as an exponent marker in scientific notation.
     */
    int EXPONENT_MARKER = 'e';

    /**
     * The ASCII value for an uppercase 'E', often used as an exponent marker in scientific notation.
     */
    int EXPONENT_MARKER2 = 'E';

    /**
     * String representation of the minimum integer value.
     */
    String MIN_INT_STR = String.valueOf(Integer.MIN_VALUE);

    /**
     * String representation of the maximum integer value.
     */
    String MAX_INT_STR = String.valueOf(Integer.MAX_VALUE);

    /**
     * String representation of the minimum long value.
     */
    String MIN_LONG_STR = String.valueOf(Long.MIN_VALUE);

    /**
     * String representation of the maximum long value.
     */
    String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);

    /**
     * The length of the string representation of the minimum integer value.
     */
    int MIN_INT_STR_LENGTH = MIN_INT_STR.length();

    /**
     * The length of the string representation of the maximum integer value.
     */
    int MAX_INT_STR_LENGTH = MAX_INT_STR.length();

    /**
     * The length of the string representation of the minimum long value.
     */
    int MIN_LONG_STR_LENGTH = MIN_LONG_STR.length();

    /**
     * The length of the string representation of the maximum long value.
     */
    int MAX_LONG_STR_LENGTH = MAX_LONG_STR.length();

    /**
     * The ASCII value for a dot or period, used in decimal numbers.
     */
    int DOT = '.';
    /**
     * The ASCII value for a single quote.
     */
    int SINGLE_QUOTE = '\'';

    /**
     * The ASCII value for the capital letter 'A'.
     */
    int A = 'A';

    /**
     * The ASCII value for the capital letter 'B'.
     */
    int B = 'B';

    /**
     * The ASCII value for the capital letter 'C'.
     */
    int C = 'C';

    /**
     * The ASCII value for the capital letter 'D'.
     */
    int D = 'D';

    /**
     * The ASCII value for the capital letter 'E'.
     */
    int E = 'E';

    /**
     * The ASCII value for the capital letter 'F'.
     */
    int F = 'F';

    /**
     * The ASCII value for the capital letter 'G'.
     */
    int G = 'G';

    /**
     * The ASCII value for the capital letter 'H'.
     */
    int H = 'H';

    /**
     * The ASCII value for the capital letter 'I'.
     */
    int I = 'I';

    /**
     * The ASCII value for the capital letter 'J'.
     */
    int J = 'J';

    /**
     * The ASCII value for the capital letter 'K'.
     */
    int K = 'K';

    /**
     * The ASCII value for the capital letter 'L'.
     */
    int L = 'L';

    /**
     * The ASCII value for the capital letter 'M'.
     */
    int M = 'M';

    /**
     * The ASCII value for the capital letter 'N'.
     */
    int N = 'N';

    /**
     * The ASCII value for the capital letter 'O'.
     */
    int O = 'O';

    /**
     * The ASCII value for the capital letter 'P'.
     */
    int P = 'P';

    /**
     * The ASCII value for the capital letter 'Q'.
     */
    int Q = 'Q';

    /**
     * The ASCII value for the capital letter 'R'.
     */
    int R = 'R';

    /**
     * The ASCII value for the capital letter 'S'.
     */
    int S = 'S';

    /**
     * The ASCII value for the capital letter 'T'.
     */
    int T = 'T';

    /**
     * The ASCII value for the capital letter 'U'.
     */
    int U = 'U';

    /**
     * The ASCII value for the capital letter 'V'.
     */
    int V = 'V';

    /**
     * The ASCII value for the capital letter 'W'.
     */
    int W = 'W';

    /**
     * The ASCII value for the capital letter 'X'.
     */
    int X = 'X';

    /**
     * The ASCII value for the capital letter 'Y'.
     */
    int Y = 'Y';

    /**
     * The ASCII value for the capital letter 'Z'.
     */
    int Z = 'Z';


    /**
     * The ASCII value for the character 'a'.
     */
    int A_ = 'a';

    /**
     * The ASCII value for the character 'b'.
     */
    int B_ = 'b';

    /**
     * The ASCII value for the character 'c'.
     */
    int C_ = 'c';
    /**
     * The ASCII value for the character 'd'.
     */
    int D_ = 'd';

    /**
     * The ASCII value for the character 'e'.
     */
    int E_ = 'e';

    /**
     * The ASCII value for the character 'f'.
     */
    int F_ = 'f';

    /**
     * The ASCII value for the character 'g'.
     */
    int G_ = 'g';

    /**
     * The ASCII value for the character 'h'.
     */
    int H_ = 'h';

    /**
     * The ASCII value for the character 'i'.
     */
    int I_ = 'i';

    /**
     * The ASCII value for the character 'j'.
     */
    int J_ = 'j';

    /**
     * The ASCII value for the character 'k'.
     */
    int K_ = 'k';

    /**
     * The ASCII value for the character 'l'.
     */
    int L_ = 'l';

    /**
     * The ASCII value for the character 'm'.
     */
    int M_ = 'm';

    /**
     * The ASCII value for the character 'n'.
     */
    int N_ = 'n';

    /**
     * The ASCII value for the character 'o'.
     */
    int O_ = 'o';

    /**
     * The ASCII value for the character 'p'.
     */
    int P_ = 'p';

    /**
     * The ASCII value for the character 'q'.
     */
    int Q_ = 'q';

    /**
     * The ASCII value for the character 'r'.
     */
    int R_ = 'r';

    /**
     * The ASCII value for the character 's'.
     */
    int S_ = 's';

    /**
     * The ASCII value for the character 't'.
     */
    int T_ = 't';

    /**
     * The ASCII value for the character 'u'.
     */
    int U_ = 'u';

    /**
     * The ASCII value for the character 'v'.
     */
    int V_ = 'v';

    /**
     * The ASCII value for the character 'w'.
     */
    int W_ = 'w';

    /**
     * The ASCII value for the character 'x'.
     */
    int X_ = 'x';

    /**
     * The ASCII value for the character 'y'.
     */
    int Y_ = 'y';

    /**
     * The ASCII value for the character 'z'.
     */
    int Z_ = 'z';
}

```

####### NumberParseResult.java

```java
/*
 * Copyright 2013-2023 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.nats.jparse.node.support;

import java.util.Objects;

/**
 * Represents the result of a number parsing operation.
 * <p>
 * The NumberParseResult class represents the result of a number parsing operation.
 * It provides methods to access the end index of the parsed number and to check if the parsed number was a float.
 * The class also overrides the equals, hashCode, and toString methods for proper object comparison and string
 * representation.
 */
public final class NumberParseResult {
    private final int endIndex;
    private final boolean wasFloat;

    /**
     * Constructs a new NumberParseResult.
     *
     * @param endIndex the end index of the parsed number
     * @param wasFloat indicates whether the parsed number was a float
     */
    public NumberParseResult(int endIndex, boolean wasFloat) {
        this.endIndex = endIndex;
        this.wasFloat = wasFloat;
    }

    /**
     * Returns the end index of the parsed number.
     *
     * @return the end index of the parsed number
     */
    public int endIndex() {
        return endIndex;
    }

    /**
     * Indicates whether the parsed number was a float.
     *
     * @return true if the parsed number was a float, false otherwise
     */
    public boolean wasFloat() {
        return wasFloat;
    }

    /**
     * Checks if this NumberParseResult is equal to another object.
     *
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        final NumberParseResult that = (NumberParseResult) obj;
        return this.endIndex == that.endIndex &&
                this.wasFloat == that.wasFloat;
    }

    /**
     * Generates a hash code for this NumberParseResult.
     *
     * @return the hash code value for this NumberParseResult
     */
    @Override
    public int hashCode() {
        return Objects.hash(endIndex, wasFloat);
    }

    /**
     * Returns a string representation of this NumberParseResult.
     *
     * @return a string representation of this NumberParseResult
     */
    @Override
    public String toString() {
        return "NumberParseResult[" +
                "endIndex=" + endIndex + ", " +
                "wasFloat=" + wasFloat + ']';
    }
}

```

