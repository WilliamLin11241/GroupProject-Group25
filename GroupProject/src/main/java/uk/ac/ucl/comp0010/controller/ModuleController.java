package uk.ac.ucl.comp0010.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.ac.ucl.comp0010.model.Module;
import uk.ac.ucl.comp0010.repository.ModuleRepository;

@RestController
@RequestMapping("/Module")
public class ModuleController {

    private final ModuleRepository moduleRepository;

    public ModuleController(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    /**
     * Create a new module.
     *
     * @param params module_code, name, and mnc
     * @return the created module
     */
    @PostMapping("/addModule")
    public ResponseEntity<Module> addModule(@RequestBody Map<String, String> params) {
        Module module = new Module();
        module.setCode(params.get("module_code"));
        module.setName(params.get("name"));
        module.setMnc(Boolean.parseBoolean(params.get("mnc")));
        module = moduleRepository.save(module);

        return ResponseEntity.ok(module);
    }

    /**
     * Retrieve all modules.
     *
     * @return list of all modules
     */
    @GetMapping("/all")
    public ResponseEntity<List<Module>> getAllModules() {
        List<Module> modules = (List<Module>) moduleRepository.findAll();
        return ResponseEntity.ok(modules);
    }

    /**
     * Retrieve a module by its code.
     *
     * @param code the module code
     * @return the module object
     */
    @GetMapping("/{code}")
    public ResponseEntity<Module> getModuleByCode(@PathVariable String code) {
        Module module = moduleRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Module not found with code: " + code));
        return ResponseEntity.ok(module);
    }

    /**
     * Update an existing module.
     *
     * @param code   the module code
     * @param params name and mnc
     * @return the updated module
     */
    @PutMapping("/update/{code}")
    public ResponseEntity<Module> updateModule(@PathVariable String code, @RequestBody Map<String, String> params) {
        Module module = moduleRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Module not found with code: " + code));

        module.setName(params.get("name"));
        module.setMnc(Boolean.parseBoolean(params.get("mnc")));
        module = moduleRepository.save(module);

        return ResponseEntity.ok(module);
    }

    /**
     * Delete a module by its code.
     *
     * @param code the module code
     * @return response message
     */
    @DeleteMapping("/delete/{code}")
    public ResponseEntity<String> deleteModule(@PathVariable String code) {
        Module module = moduleRepository.findById(code)
                .orElseThrow(() -> new RuntimeException("Module not found with code: " + code));

        moduleRepository.delete(module);
        return ResponseEntity.ok("Module deleted successfully");
    }
}
