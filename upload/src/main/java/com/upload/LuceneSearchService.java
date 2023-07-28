package com.upload;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LuceneSearchService {
    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    @Value("${index.directory}")
    private String indexDirectory;

    public List<Long> searchDocuments(String query) throws IOException, ParseException {
        Path indexPath = Paths.get(indexDirectory);
        Directory indexDir = FSDirectory.open(indexPath);
        IndexReader indexReader = DirectoryReader.open(indexDir);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        Analyzer analyzer = new StandardAnalyzer();
        String[] fields = {"content"};
        QueryParser queryParser = new MultiFieldQueryParser(fields, analyzer);
        Query luceneQuery = queryParser.parse(QueryParser.escape(query));

        int topHits = 50; // Number of top hits to retrieve
        TopDocs topDocs = indexSearcher.search(luceneQuery, topHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        List<Long> recordIds = new ArrayList<>();
        for (ScoreDoc scoreDoc : scoreDocs) {
            int docId = scoreDoc.doc;
            Document document = indexSearcher.doc(docId);
            Long recordId = Long.valueOf(document.get("recordId"));
            System.out.println("recordId :"+recordId);
            recordIds.add(recordId);
        }

        indexReader.close();
        return recordIds;
    }

    public List<String> searchFilesByContent(String query) {
        try {
            List<Long> recordIds = searchDocuments(query);

            List<String> fileNamesWithDetails = new ArrayList<>();
            for (long recordId : recordIds) {
//                UploadedFile uploadedFile = uploadedFileRepository.findByFileName(fileName);
                Optional<UploadedFile> fileDetails = uploadedFileRepository.findById(recordId);

                String fileName = fileDetails.get().getFileName();
                System.out.println("fileName : "+fileName);
                if (fileDetails != null) {
                    fileNamesWithDetails.add(fileDetails.get().getFileName());
                }
            }
            System.out.println("fileNamesWithDetails:"+fileNamesWithDetails);

            return fileNamesWithDetails;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
