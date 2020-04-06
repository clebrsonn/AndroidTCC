package com.hyteck.project;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchOptions implements Parcelable {

    private Map<String, String> options;


    public SearchOptions(List<TextView> options) {
        this.options = options.stream().collect(Collectors.toMap(editText -> editText.getHint().toString(), editText -> editText.getText().toString()));


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.options.size());
        for (Map.Entry<String, String> entry : this.options.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    protected SearchOptions(Parcel in) {
        int optionsSize = in.readInt();
        this.options = new HashMap<String, String>(optionsSize);
        for (int i = 0; i < optionsSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.options.put(key, value);
        }
    }

    public static final Parcelable.Creator<SearchOptions> CREATOR = new Parcelable.Creator<SearchOptions>() {
        @Override
        public SearchOptions createFromParcel(Parcel source) {
            return new SearchOptions(source);
        }

        @Override
        public SearchOptions[] newArray(int size) {
            return new SearchOptions[size];
        }
    };
}
