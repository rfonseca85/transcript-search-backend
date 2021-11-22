package ca.rfonseca85.transcript.search.utils;

import ca.rfonseca85.transcript.search.models.TranscriptData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WebvttParser {

    public List<TranscriptData> parse(InputStream inputStream) throws IOException {

        List<TranscriptData> transcriptDataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            String startTime = "";
            String endTime = "";
            while ((line = br.readLine()) != null) {
                if (line != null && !line.equals("") && !line.equals("WEBVTT")) {
                    if (!line.startsWith("<")) {
                        startTime = line.split(" --> ")[0];
                        endTime = line.split(" --> ")[1];
                    } else {
                        TranscriptData transcriptData = new TranscriptData();
                        transcriptData.setStartTime(startTime);
                        transcriptData.setEndTime(endTime);
                        startTime = new String();
                        endTime = new String();
                        transcriptData.setVideoName(line.split(">")[0].replace("<v ", ""));
                        transcriptData.setPhrase(line.split(">")[1].replace("</v", ""));
                        transcriptDataList.add(transcriptData);
                    }
                }
            }
        }

        return transcriptDataList;
    }

}