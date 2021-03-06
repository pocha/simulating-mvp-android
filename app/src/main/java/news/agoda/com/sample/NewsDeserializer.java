package news.agoda.com.sample;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocha on 20/03/17.
 */
public class NewsDeserializer implements JsonDeserializer<List<NewsEntity>>
{
    @Override
    public List<NewsEntity> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException
    {
        // Get the "content" element from the parsed JSON
        List<NewsEntity> newsItemList = new ArrayList<>();

        if (je.getAsJsonObject().get("results").isJsonArray()) {
            JsonArray results = je.getAsJsonObject().getAsJsonArray("results");
            for (int i = 0; i < results.size(); i++) {
                JsonObject newsObject = results.get(i).getAsJsonObject();
                String title = newsObject.get("title").getAsString();
                String summary = newsObject.get("abstract").getAsString();
                String url = newsObject.get("url").getAsString();
                List<MediaEntity> mediaEntityList = new ArrayList<>();
                if (newsObject.get("multimedia").isJsonArray()) {
                    JsonArray multimedia = newsObject.getAsJsonArray("multimedia");
                    for (int j = 0; j < multimedia.size(); j++) {
                        MediaEntity mediaEntity = new MediaEntity(multimedia.get(j).getAsJsonObject().get("url").getAsString());
                        mediaEntityList.add(mediaEntity);
                    }
                }
                NewsEntity newsEntity = new NewsEntity(title, summary, url, mediaEntityList);
                if (title != null) {
                    newsItemList.add(newsEntity);
                }
            }
        }
        return newsItemList;
    }
}
