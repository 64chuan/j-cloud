/*
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
package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.io.Serializable;


/**
 * 用户信息定义
 *
 * @author CD826(CD826Dong@gmail.com)
 * @since 1.0.0
 */
@Getter
@Setter
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    // ========================================================================
    // fields =================================================================
    private Long id;
    private String nickname;                                // 昵称
    private String avatar;                                  // 用户头像

    public UserDto() {

    }


    @SneakyThrows
    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("id", getId());
        object.put("nickname", getNickname());
        object.put("avatar", getAvatar());
        return object.toString();
    }
}