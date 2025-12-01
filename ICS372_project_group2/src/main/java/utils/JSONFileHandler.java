package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import dealership.entities.vehicle.Vehicle;
import dealership.entities.vehicle.VehicleFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
/**
 * A Utility class to read, parse, and write JSON data
 *
 * @see <a href="https://crunchify.com/how-to-read-json-object-from-file-in-java/"></a>
 * <a href="https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/"></a>
 */
@Component
public interface JSONFileHandler extends IFileHandler {


    /**
     * A BiFunction to convert create vehicle objects from JSON data and add to existing dealers or new Dealer object
     * @params JSONObject, Map<String, Dealer> dealerList
     */
    Function<JSONObject, Vehicle> JSONObjectToVehicle = (jsonVehicle) -> {

        String dealerId = (String) jsonVehicle.get("dealership_id");
        String vehicleType = (String) jsonVehicle.get("vehicle_type");
        String manufacturer = (String) jsonVehicle.get("vehicle_manufacturer");
        String model = (String) jsonVehicle.get("vehicle_model");
        String vehicleId = (String) jsonVehicle.get("vehicle_id");
        Number vehiclePrice = (Number) jsonVehicle.get("price");
        Long acquisition_date = (Long) jsonVehicle.get("acquisition_date");

        Vehicle vehicle = VehicleFactory.createVehicle(vehicleType);

        vehicle.setDealerId(dealerId);
        vehicle.setVehicleId(vehicleId);
        vehicle.setManufacturer(manufacturer);
        vehicle.setModel(model);
        vehicle.setPrice(vehiclePrice);
        vehicle.setAcquisitionDate(acquisition_date);


        return vehicle;
    };

    static Optional<JSONArray> uploadJSONData(String filePath) {
            JSONParser parser = new JSONParser();
            Object obj = null;
            try {
                obj = parser.parse(new FileReader(filePath));
            } catch (Exception e) {
                System.err.println(e.getMessage() + "\nCould not parse file. The file has no json object data");
            }

            JSONObject jsonObject = (JSONObject) obj;
            if (jsonObject == null || jsonObject.isEmpty()) return Optional.empty();
            return Optional.of((JSONArray) jsonObject.get("car_inventory"));
        }

    /**
     * @return List<Vehicle> of new vehicles from JSONFile
     * @params An array of JSON Objects
     * @params A key value map of dealers by id
     */
    static List<Vehicle> parse(JSONArray jsonArray) {
        List<Vehicle> newVehicles = new ArrayList<>();

        if (jsonArray.isEmpty()) return newVehicles;

        for (Object obj : jsonArray) {
            Vehicle vehicle = JSONFileHandler.JSONObjectToVehicle.apply((JSONObject) obj);
            newVehicles.add(vehicle);
        }

        return newVehicles;
    }

     static void exportVehicles(List<Vehicle> vehicleList, String dealerId) {
         // create object mapper instance
         ObjectMapper mapper = new ObjectMapper();
         FilterProvider filters = new SimpleFilterProvider()
                 .addFilter("filter properties by name", SimpleBeanPropertyFilter.serializeAllExcept("parsedAcquisitionDate"));

         ObjectWriter writer = mapper.writer(filters);

         try {
             writer.withDefaultPrettyPrinter().writeValue(Paths.get("src/main/resources/vehicle-storage/dealerVehicles_" + dealerId + ".json").toFile(), vehicleList);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }
}

