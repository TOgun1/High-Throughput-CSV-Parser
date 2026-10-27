package com.TOgun1.csv_parser.service;
import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Service;

import com.TOgun1.csv_parser.model.Shopper;
import com.TOgun1.csv_parser.repository.ShopperRepository;
@Service
public class Ingestion1 {
    private final ShopperRepository shopperRepository;

    public Ingestion1(ShopperRepository shopperRepository) {
        this.shopperRepository = shopperRepository;
    }

    public void ingestData(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String line;
            boolean isFirstLine = true;
            //Skip first line representing the header
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                Shopper shopper = new Shopper();
                
                shopper.setUserId(Long.parseLong(data[0]));
                shopper.setAge(Integer.parseInt(data[1]));
                shopper.setGender(data[2]);
                shopper.setCountry(data[3]);
                shopper.setIncomeLevel(data[5]); 
                shopper.setMonthlySpend(Double.parseDouble(data[16])); 
                shopper.setAverageOrderValue(Double.parseDouble(data[17])); 
                shopper.setPreferredPaymentMethod(data[18]);
                shopper.setLoyaltyProgramMember(Boolean.parseBoolean(data[20]));
                
                shopperRepository.save(shopper);
            }
        
        } catch(Exception e){
            throw new RuntimeException("Could not parse CSV" + e.getMessage());
        }

    }
}
