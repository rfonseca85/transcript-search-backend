package ca.rfonseca85.transcript.search.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "transcriptdataindex")
public class TranscriptData
{
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "groupName")
    private String groupName;

    @Field(type = FieldType.Text, name = "videoName")
    private String videoName;

    @Field(type = FieldType.Text, name = "phrase")
    private String phrase;

    @Field(type = FieldType.Text, name = "startTime")
    private String startTime;

    @Field(type = FieldType.Text, name = "endTime")
    private String endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
