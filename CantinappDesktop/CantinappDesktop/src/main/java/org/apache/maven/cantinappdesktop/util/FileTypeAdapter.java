package org.apache.maven.cantinappdesktop.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.IOException;

public class FileTypeAdapter extends TypeAdapter<File> {

    @Override
    public void write(JsonWriter out, File value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.value(value.getPath());
    }

    @Override
    public File read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        }
        return new File(in.nextString());
    }
}