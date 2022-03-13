package spms.api.weather;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import spms.vo.Weather;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherAPI {

    // WeatherValue 값들로 존재하는 enum 생성
    enum WeatherValue {
        PTY, REH, RN1, T1H, UUU, VEC, VVV, WSD
    }

    // Weather 시간으로 존재할 수 있는 enum 생성
    enum WeatherBaseTime {
        _0000, _0300, _0600, _0900, _1200, _1500, _1800, _2100
    }


    // 해당하는 api 가 필요로 하는 변수
    private String apiURL;
    private String authKey;

    public WeatherAPI(String apiURL, String authKey) {
        this.apiURL = apiURL;
        this.authKey = authKey;
    }

    public Weather use(String x, String y, String baseDate, String baseTime) throws Exception {
        Weather weather = new Weather();

        // URL 을 위한 문자열 생성
        StringBuilder urlBuilder = new StringBuilder(apiURL);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + authKey);
        urlBuilder.append("&" + URLEncoder.encode("numOfRows=10", "UTF-8"));    // 숫자 표
        urlBuilder.append("&" + URLEncoder.encode("pageNo=1", "UTF-8"));    // 페이지 수
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜*/
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(x, "UTF-8")); //경도
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(y, "UTF-8")); //위도

        // 문자열 기반으로 URL 생성
        URL url = new URL(urlBuilder.toString());

        // Connection 을 열고 결과 값을 받을 수 있도록 버퍼 사용
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + connection.getResponseCode());
        BufferedReader br;
        if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        // StringBuilder 사용으로 버퍼에 있는 값들을 순차적으로 저장해서 문자열 생성
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        // 종료 절차 수행
        br.close();
        connection.disconnect();
        String result = sb.toString();

        // 객체에 저장하기 위한 과정으로,
        // 문자열을 문자열을 읽을 수 있는 InputSource 생성 후 Document 타입으로 변환
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(result));
        Document document = db.parse(is);

        // document 에서 특정 element를 반복하는 반복문을 사용해서  값들을 가져오도록 함.
        try {
            document.getDocumentElement().normalize();
            System.out.println("Root Element: " + document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName("item");
            System.out.println("-----------------------");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String category = element.getElementsByTagName("category").item(0).getTextContent();
                    double value = Double.parseDouble(element.getElementsByTagName("obsrValue").item(0).getTextContent());

                    WeatherValue weatherValue = WeatherValue.valueOf(category);
                    switch (weatherValue) {
                        case PTY:
                            weather.setPTY(value);
                            break;
                        case REH:
                            weather.setREH(value);
                            break;
                        case RN1:
                            weather.setRN1(value);
                            break;
                        case T1H:
                            weather.setT1H(value);
                            break;
                        case UUU:
                            weather.setUUU(value);
                            break;
                        case VEC:
                            weather.setVEC(value);
                            break;
                        case VVV:
                            weather.setVVV(value);
                            break;
                        case WSD:
                            weather.setWSD(value);
                            break;
                        default:
                            throw new XMLParseException();
                    }
                }
            }
        } catch (XMLParseException e) {
            e.printStackTrace();
        }
        weather.setDate(baseDate);
        weather.setTime(baseTime);

        return weather;

    }

}
