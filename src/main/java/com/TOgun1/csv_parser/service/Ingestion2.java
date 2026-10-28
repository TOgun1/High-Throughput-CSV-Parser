package com.TOgun1.csv_parser.service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Ingestion2 {
    private final JdbcTemplate jdbcTemplate;

    public Ingestion2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public long uploadCsvFast(String filePath) {
        long startTime = System.currentTimeMillis();
        int batchSize = 10000;
        
        
        String sql = "INSERT INTO shoppers (user_id, age, gender, country, income_level, monthly_spend, average_order_value, preferred_payment_method, loyalty_program_member) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            List<Object[]> batch = new ArrayList<>();
            int totalRecords = 0;

            while ((line = br.readLine()) != null) {
                
                String[] data = line.split(",");
                
                batch.add(new Object[]{
                        Long.parseLong(data[0]),                               
                        Integer.parseInt(data[1]),      
                        data[2],                               
                        data[3],                               
                        data[5],                               
                        Double.parseDouble(data[16].trim()),    
                        Double.parseDouble(data[17].trim()),    
                        data[18],                               
                        Boolean.parseBoolean(data[20].trim())
                });

                totalRecords++;

                // Execute the batch every 10,000 rows
                if (batch.size() == batchSize) {
                    jdbcTemplate.batchUpdate(sql, batch);
                    batch.clear();
                    System.out.println("Inserted " + totalRecords + " rows...");
                }
            }

            // Inserts potential remaining rows
            if (!batch.isEmpty()) {
                jdbcTemplate.batchUpdate(sql, batch);
                System.out.println("Inserted final chunk. Total rows: " + totalRecords);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }

        return (System.currentTimeMillis() - startTime) / 1000; // Return time in seconds
    }
}
