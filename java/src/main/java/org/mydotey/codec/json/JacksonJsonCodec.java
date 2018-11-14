package org.mydotey.codec.json;

import com.fasterxml.jackson.core.JsonGenerator.Feature;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

import org.mydotey.codec.CodecException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by Qiang Zhao on 10/05/2016.
 */
public class JacksonJsonCodec extends JsonCodec {

    public static final JacksonJsonCodec DEFAULT;

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(Feature.AUTO_CLOSE_TARGET, false);
        objectMapper.configure(Feature.IGNORE_UNKNOWN, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
        objectMapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        DEFAULT = new JacksonJsonCodec(objectMapper);
    }

    private ObjectMapper _objectMapper;

    public JacksonJsonCodec(ObjectMapper objectMapper) {
        Objects.requireNonNull(objectMapper, "objectMapper is null");

        _objectMapper = objectMapper;
    }

    @Override
    protected byte[] doEncode(Object obj) {
        try {
            return _objectMapper.writeValueAsBytes(obj);
        } catch (RuntimeException | Error ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CodecException(ex);
        }
    }

    @Override
    protected <T> T doDecode(byte[] is, Class<T> clazz) {
        try {
            return _objectMapper.readValue(is, clazz);
        } catch (RuntimeException | Error ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CodecException(ex);
        }
    }

    @Override
    protected void doEncode(OutputStream os, Object obj) {
        try {
            _objectMapper.writeValue(os, obj);
        } catch (RuntimeException | Error ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CodecException(ex);
        }
    }

    @Override
    protected <T> T doDecode(InputStream is, Class<T> clazz) {
        try {
            return _objectMapper.readValue(is, clazz);
        } catch (RuntimeException | Error ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CodecException(ex);
        }
    }

}
