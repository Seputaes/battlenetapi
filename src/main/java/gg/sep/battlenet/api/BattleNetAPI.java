/*
 * Copyright (c) 2019 sep.gg <seputaes@sep.gg>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package gg.sep.battlenet.api;

import java.io.IOException;
import java.util.Optional;

import com.google.gson.JsonParseException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import retrofit2.Call;

import gg.sep.battlenet.BattleNet;
import gg.sep.battlenet.model.BattleNetEntity;

/**
 * Abstract class which is the basis for each of the individual Battle.net API classes.
 *
 * This class provides some shared methods common between each of the API classes,
 * such as executing API calls and returning the API's objects.
 */
@Log4j2
@RequiredArgsConstructor
public abstract class BattleNetAPI {
    @Getter(AccessLevel.PROTECTED)
    private final BattleNet battleNet;

    /**
     * Executes the API call and converts the response body into the {@link BattleNetEntity}.
     *
     * If there was an error or parsing was unsuccessful, the error will be logged and this method
     * will return an empty {@link Optional}, otherwise will return an {@link Optional} of the API object.
     *
     * @param call Retrofit API call to execute.
     * @param <T> Type of the API response object that is expected to be returned from the API call.
     * @return Optional of type {@code T}, which represents the API response object.
     */
    protected <T extends BattleNetEntity> Optional<T> executeCall(final Call<T> call) {
        try {
            return battleNet.getProxy().getResponse(call);
        } catch (final IOException | JsonParseException e) {
            log.error(e);
            return Optional.empty();
        }
    }
}
