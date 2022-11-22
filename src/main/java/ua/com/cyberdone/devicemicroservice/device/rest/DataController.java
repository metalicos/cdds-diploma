package ua.com.cyberdone.devicemicroservice.device.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.cyberdone.devicemicroservice.device.model.DataDTO;
import ua.com.cyberdone.devicemicroservice.device.service.DataService;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/device/hydroponic/v1/data", produces = MediaType.APPLICATION_JSON_VALUE)
public class DataController {
    private final DataService dataService;

    @GetMapping
    public ResponseEntity<Page<DataDTO>> getAllData(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                    @RequestParam(defaultValue = "20", required = false) Integer size) {
        return ResponseEntity.ok(dataService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDTO> getData(@PathVariable Long id) {
        return ResponseEntity.ok(dataService.get(id));
    }
}
