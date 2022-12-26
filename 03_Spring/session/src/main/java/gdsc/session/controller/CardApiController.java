package gdsc.session.controller;

import gdsc.session.DTO.StoreDTO;
import gdsc.session.entity.Store;
import gdsc.session.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CardApiController {
    private final StoreRepository storeRepository;

    @GetMapping("/cardApi")
    public Long cardApiParsing() {
        // https://api.odcloud.kr/api/15088598/v1/uddi:623374f5-abcd-4531-8078-914e6752c1df
        // ?page=1&
        // perPage=10&
        // serviceKey=%205zlD6YfkYpB0OAVmDWZUt%2FSUtuUq4zcoQCpHgFMAfKmBT5GqM5D7zKpv2dF4Aq7%2F%2FRmfVABg64w1BXENEgZ5WA%3D%3D%20

        StringBuilder sb = new StringBuilder();

        try {
            StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15088598/v1/uddi:623374f5-abcd-4531-8078-914e6752c1df");
            urlBuilder.append("?");
            urlBuilder.append(URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&");
            urlBuilder.append(URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
            urlBuilder.append("&");
            urlBuilder.append(URLEncoder.encode("serviceKey", "UTF-8") + "=" + "%205zlD6YfkYpB0OAVmDWZUt%2FSUtuUq4zcoQCpHgFMAfKmBT5GqM5D7zKpv2dF4Aq7%2F%2FRmfVABg64w1BXENEgZ5WA%3D%3D%20");

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader br;

            if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            } else {
                log.info("fail");
                return 0L;
            }

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            br.close();
            connection.disconnect();

        } catch (Exception e) { e.printStackTrace(); }

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
            JSONArray data = (JSONArray) jsonObject.get("data");

            for (int i = 0; i < data.size(); i++) {
                JSONObject obj = (JSONObject) data.get(i);

                if (obj.get("위도") == null || obj.get("경도") == null) {
                    continue;
                }

                String storeName = (String) obj.get("가맹점명");
                String storeAddress = (String) obj.get("가맹점주소");
                Double storeLat = Double.parseDouble((String) obj.get("위도"));
                Double storeLong = Double.parseDouble((String) obj.get("경도"));
                String storeSector = (String) obj.get("업종명");

                StoreDTO storeDTO = StoreDTO.builder()
                        .name(storeName)
                        .address(storeAddress)
                        .latitude(storeLat)
                        .longitude(storeLong)
                        .sector(storeSector)
                        .build();

                Store store = Store.builder()
                        .storeDTO(storeDTO)
                        .build();

                storeRepository.save(store);
            }

        } catch (Exception e) { e.printStackTrace(); }

        return 1L;
    }
}
