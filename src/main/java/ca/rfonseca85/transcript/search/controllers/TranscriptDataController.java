package ca.rfonseca85.transcript.search.controllers;

import ca.rfonseca85.transcript.search.models.TranscriptData;
import ca.rfonseca85.transcript.search.services.TranscriptDataService;
import ca.rfonseca85.transcript.search.utils.WebvttParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/transcript")
public class TranscriptDataController {

    @Autowired
    private TranscriptDataService transcriptDataService;

    @GetMapping
    public List<TranscriptData> searchTranscriptDataByVideoName(@RequestParam("videoName") String videoName) {
        List<TranscriptData> transcriptDataList = transcriptDataService.getAllTranscriptDataForVideoName(videoName);

        return transcriptDataList;
    }

    @GetMapping("/search")
    public List<TranscriptData> searchTranscriptDataByTerm(@RequestParam("term") String term) {
        return transcriptDataService.findBySearchTerm(term);
    }

    @GetMapping("/search2")
    public List<TranscriptData> searchTranscriptDataByPersonAndPhrase(@RequestParam("person") String person, @RequestParam("phrase") String phrase) {
        return transcriptDataService.findByPersonAndPhrase(person, phrase);
    }

    @PostMapping
    public TranscriptData addTranscriptData(@RequestBody TranscriptData transcriptData) {

        return transcriptDataService.createTranscriptDataIndex(transcriptData);
    }

    @PostMapping("/createInBulk")
    public List<TranscriptData> addTranscriptDataInBulk(@RequestBody List<TranscriptData> transcriptDataList) {
        return (List<TranscriptData>) transcriptDataService.createTranscriptDataIndices(transcriptDataList);
    }

    @PostMapping("/uploadTranscript")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("groupName") String groupName,
                                   @RequestParam("videoName") String videoName,
                                   RedirectAttributes redirectAttributes) throws IOException {

        BufferedInputStream bufferedInputStream = new BufferedInputStream(file.getInputStream());

        List<TranscriptData> transcriptDataList = (List<TranscriptData>) new WebvttParser().parse(bufferedInputStream, groupName, videoName);
        transcriptDataService.createTranscriptDataIndices(transcriptDataList);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
}
