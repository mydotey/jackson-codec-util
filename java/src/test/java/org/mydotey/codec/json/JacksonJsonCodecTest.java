package org.mydotey.codec.json;

import org.mydotey.codec.Codec;

/**
 * @author koqizhao
 *
 * Nov 8, 2018
 */
public class JacksonJsonCodecTest extends JsonCodecTest {

    @Override
    protected Codec getCodec() {
        return JacksonJsonCodec.DEFAULT;
    }

}
