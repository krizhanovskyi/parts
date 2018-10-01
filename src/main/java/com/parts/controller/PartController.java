package com.parts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.parts.model.Part;
import com.parts.service.PartService;

@Controller
public class PartController {

    private PartService service;
    private String filterMethod = "ALL";
    private String searchString = "";

    @Autowired
    public void setPartService(PartService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String greeting(){
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Page<Part> parts;
        if(searchString.isEmpty()){
            parts = filterAndSort(pageable);
        }
        else {
            parts = find(pageable);
        }
        int collect = service.findCanCollectQuantity();
        model.addAttribute("parts", parts);
        model.addAttribute("collect", collect);
        model.addAttribute("filter", filterMethod);
        return "index";
    }

    @RequestMapping(value="/search")
    public String Search(@RequestParam(value = "searchString", required = false, defaultValue = "") String searchString) {
        if(searchString.isEmpty())filterMethod = "ALL";
        this.searchString = searchString;
        return "redirect:/list";
    }

    @GetMapping("/filter/{filterM}")
    public String sortChoose(@PathVariable String filterM) {
        filterMethod = filterM;
        searchString = "";
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Part part = service.getPartById(id).get();
        model.addAttribute("part", part);
        return "operations/edit";
    }

    @PostMapping("/update")
    public String savePart(@RequestParam Integer id, @RequestParam String name, @RequestParam int quantity,
                           @RequestParam(defaultValue = "false") boolean necessary) {
        service.updatePart(id, name, quantity, necessary);
        return "redirect:/list";
    }

    @GetMapping("/new")
    public String newPart() {
        return "operations/new";
    }

    @PostMapping("/save")
    public String updatePart(@RequestParam String name, @RequestParam int quantity, @RequestParam(defaultValue = "false") boolean necessary) {
        service.savePart(new Part(name, quantity, necessary));
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.deletePart(id);
        return "redirect:/list";
    }

    private Page<Part> filterAndSort(Pageable pageable) {
        Page<Part> parts = null;
        switch (filterMethod) {
            case "NECESSARY":
                parts = service.findAllNecessary(pageable);
                break;
            case "OPTIONAL":
                parts = service.findAllOptional(pageable);
                break;
            case "ALL":
                parts = service.findAllByOrderByNameAsc(pageable);
                break;
        }
        return parts;
    }

    private Page<Part> find(Pageable pageable) {
        Page<Part> parts = service.findAllLikeName(searchString, pageable);
        return parts;
    }
}