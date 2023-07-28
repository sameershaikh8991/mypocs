package com.upload;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.poi.util.LongField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LuceneIndexerService {
    @Value("${index.directory}")
    private String indexDirectory;

    public void indexDocument(String content, String fileName,Long recordId ) throws IOException {
        Path indexPath = Paths.get(indexDirectory);
        Directory indexDir = FSDirectory.open(indexPath);
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(indexDir, config);

        Document document = new Document();
        document.add(new TextField("content", content, Field.Store.YES));
        document.add(new StringField("fileName", fileName, Field.Store.YES));
        document.add(new StoredField("recordId", recordId));

        indexWriter.addDocument(document);
        indexWriter.close();
    }
}
