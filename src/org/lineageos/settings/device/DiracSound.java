/*
 * Copyright (C) 2019 Mohammad Hasan Keramat Jahromi m.h.k.jahromi@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings.device;

import android.media.audiofx.AudioEffect;

import java.util.UUID;

class DiracSound extends AudioEffect {

    private static final int DIRACSOUND_PARAM_HEADSET_TYPE = 1;
    private static final int DIRACSOUND_PARAM_EQ_LEVEL = 2;
    private static final int DIRACSOUND_PARAM_MUSIC = 4;

    private static final UUID EFFECT_TYPE_DIRACSOUND =
            UUID.fromString("e069d9e0-8329-11df-9168-0002a5d5c51b");

    DiracSound(int priority, int audioSession) {
        super(EFFECT_TYPE_NULL, EFFECT_TYPE_DIRACSOUND, priority, audioSession);
    }

    int getMusic() throws IllegalStateException,
            IllegalArgumentException, UnsupportedOperationException {
        int[] value = new int[1];
        checkStatus(getParameter(DIRACSOUND_PARAM_MUSIC, value));
        return value[0];
    }

    void setMusic(int enable) throws IllegalStateException,
            IllegalArgumentException, UnsupportedOperationException {
        checkStatus(setParameter(DIRACSOUND_PARAM_MUSIC, enable));
    }

    int getHeadsetType() throws IllegalStateException,
            IllegalArgumentException, UnsupportedOperationException {
        int[] value = new int[1];
        checkStatus(getParameter(DIRACSOUND_PARAM_HEADSET_TYPE, value));
        return value[0];
    }

    void setHeadsetType(int type) throws IllegalStateException,
            IllegalArgumentException, UnsupportedOperationException {
        checkStatus(setParameter(DIRACSOUND_PARAM_HEADSET_TYPE, type));
    }

    void setLevel(int band, float level) throws IllegalStateException,
            IllegalArgumentException, UnsupportedOperationException {
        checkStatus(setParameter(new int[]{DIRACSOUND_PARAM_EQ_LEVEL, band},
                String.valueOf(level).getBytes()));
    }

    float getLevel(int band) throws IllegalStateException,
            IllegalArgumentException, UnsupportedOperationException {
        int[] param = new int[2];
        byte[] value = new byte[10];
        param[0] = DIRACSOUND_PARAM_EQ_LEVEL;
        param[1] = band;
        checkStatus(getParameter(param, value));
        return Float.valueOf(new String(value));
    }
}
