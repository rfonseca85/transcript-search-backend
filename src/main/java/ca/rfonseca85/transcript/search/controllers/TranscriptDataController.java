package ca.rfonseca85.transcript.search.controllers;

import ca.rfonseca85.transcript.search.models.TranscriptData;
import ca.rfonseca85.transcript.search.services.TranscriptDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/transcript")
public class TranscriptDataController
{
    @Autowired
    private TranscriptDataService transcriptDataService;

    @GetMapping
    public List<TranscriptData> searchTranscriptDataByVideoName(@RequestParam("videoName") String videoName)
    {
        List<TranscriptData> transcriptDataList = transcriptDataService.getAllTranscriptDataForVideoName(videoName);

        return transcriptDataList;
    }

    @GetMapping("/search")
    public List<TranscriptData> searchTranscriptDataByTerm(@RequestParam("term") String term)
    {
        return transcriptDataService.findBySearchTerm(term);
    }

    @PostMapping
    public TranscriptData addTranscriptData(@RequestBody TranscriptData transcriptData)
    {

        return transcriptDataService.createTranscriptDataIndex(transcriptData);
    }

    @PostMapping("/createInBulk")
    public  List<TranscriptData> addTranscriptDataInBulk(@RequestBody List<TranscriptData> transcriptDataList)
    {
        return (List<TranscriptData>) transcriptDataService.createTranscriptDataIndices(transcriptDataList);
    }
}
