package ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.model.DataDTO;
import ua.com.cyberdone.devicemicroservice.device.hydroponic.v1.service.DataService;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * API для отримування даних із гідропоніки
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/device/hydroponic/v1/data", produces = MediaType.APPLICATION_JSON_VALUE)
public class DataController {
    private final DataService dataService;

    @GetMapping("/byDevice/{uuid}")
    public ResponseEntity<Page<DataDTO>> getAllData(@PathVariable String uuid,
                                                    @RequestParam(defaultValue = "0", required = false) Integer page,
                                                    @RequestParam(defaultValue = "20", required = false) Integer size) {
        return ResponseEntity.ok(dataService.findAllByDeviceUuid(uuid, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDTO> getData(@PathVariable Long id) {
        return ResponseEntity.ok(dataService.get(id));
    }

    @GetMapping("/byDevice/{uuid}/range")
    public ResponseEntity<Page<DataDTO>> getLastDataInDeviceWithUuid(@RequestHeader(AUTHORIZATION) String token,
                                                                     @PathVariable String uuid,
                                                                     @RequestParam String fromDate,
                                                                     @RequestParam String toDate,
                                                                     @RequestParam int dataStep) {
        return ResponseEntity.ok(dataService.findAllInRangeWithMinutesStep(uuid, fromDate, toDate, dataStep));
    }
}
