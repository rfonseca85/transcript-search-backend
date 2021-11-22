package ca.rfonseca85.transcript.search.repositories;

import ca.rfonseca85.transcript.search.models.TranscriptData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface TranscriptDataRepository extends ElasticsearchRepository<TranscriptData, String>
{
    List<TranscriptData> findByVideoName(String videoName);

    List<TranscriptData> findByPhraseContaining(String phrase);
}
