package ca.rfonseca85.transcript.search.services;

import ca.rfonseca85.transcript.search.models.TranscriptData;
import ca.rfonseca85.transcript.search.repositories.TranscriptDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TranscriptDataService
{
    @Autowired
    private TranscriptDataRepository transcriptDataRepository;

    public TranscriptData createTranscriptDataIndex(final TranscriptData transcriptData)
    {
        return transcriptDataRepository.save(transcriptData);
    }

    public Iterable<TranscriptData> createTranscriptDataIndices(final List<TranscriptData> transcriptDataList)
    {
        return transcriptDataRepository.saveAll(transcriptDataList);
    }

    public List<TranscriptData> getAllTranscriptDataForVideoName (String videoName)
    {
        return transcriptDataRepository.findByVideoName(videoName);
    }

    public TranscriptData findById (String id)
    {
        return transcriptDataRepository.findById(id).get();
    }

    public List<TranscriptData> findBySearchTerm (String term)
    {
        return transcriptDataRepository.findByPhraseContaining(term);
    }

}
