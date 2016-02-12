package fr.esilv.myapplication2.mobiledev.app.Station;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by louis on 12/02/2016.
 */
class MyDeserializer<T> implements JsonDeserializer<T>
{
    @Override
    public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException
    {
        // Get the "content" element from the parsed JSON
        JsonElement content = je.getAsJsonObject().get("position");

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion
        // to this deserializer
        return new Gson().fromJson(content, type);

    }
}
