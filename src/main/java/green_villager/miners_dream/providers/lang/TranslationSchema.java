package green_villager.miners_dream.providers.lang;

import java.util.List;
import javax.annotation.processing.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class TranslationSchema {

    @SerializedName("translations")
    @Expose
    public List<Translation> translations;

    public class Translation {

        @SerializedName("key")
        @Expose
        public String key;
        @SerializedName("ja_jp")
        @Expose
        public String jaJp;
        @SerializedName("en_us")
        @Expose
        public String enUs;

    }
}

