package com.buletjournal.project.controller;


import com.buletjournal.project.exception.ResourceNotFound;
import com.buletjournal.project.model.EntryNote;
import com.buletjournal.project.repository.JournalRepository;
import com.buletjournal.project.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Controller
public class JournalController {
    @Autowired
    private JournalService journalService;
    @Autowired
    private JournalRepository journalRepository;


    @RequestMapping({"/", "/notes", "/search"})
    public String home(EntryNote note, Model model, String keyword) {
        if (keyword != null) {
            List<EntryNote> list = journalService.findByKeynote(keyword);
            model.addAttribute("list", list);
        } else {
            List<EntryNote> list = journalService.getAllNotes();
            model.addAttribute("list", list);
        }
        return "index";
    }
//@GetMapping("/try/{tab}")
//public String tab(@PathVariable String tab) {
//    if (Arrays.asList("add", "tab2")
//            .contains(tab)) {
//        return "_" + tab;
//    }
//
//    return "empty";
//}


    @GetMapping("/add")
    public String showAddForm(EntryNote note, Model model) {
        model.addAttribute("note", note);
        return "add-form";
    }

    @PostMapping("/save")
    public String create(EntryNote note, Model model) throws ParseException {
        journalService.createNoteOrUpdate(note);
        return "redirect:/";
    }

    @RequestMapping(path = {"/update", "/update/{id}"})
    public String update(Model model, @PathVariable("id") Integer id) throws ResourceNotFound {
        if (id != null) {
            EntryNote newnote = journalService.getNoteById(id);
            model.addAttribute("note", newnote);
        } else {
            model.addAttribute("note", new EntryNote());
        }
        return "add-form";
    }

    @RequestMapping(path = {"/delete/{id}"})
    public String delete(Model model, @PathVariable("id") Integer id) {
        journalService.deleteNoteById(id);
        return "redirect:/";
    }

    @GetMapping("/tasks")
    public String showTasks(EntryNote note, Model model) {
        List<EntryNote> list = journalService.findTasks();
        model.addAttribute("list", list);
        return "tasks";
    }

    @GetMapping("/events")
    public String showEvents(EntryNote note, Model model) {
        List<EntryNote> list = journalService.findEvents();
        model.addAttribute("list", list);
        return "events";
    }

    @GetMapping("/appointments")
    public String showAppointments(EntryNote note, Model model) {
        List<EntryNote> list = journalService.findAppointments();
        model.addAttribute("list", list);
        return "appointments";
    }

    @GetMapping("/in_progress")
    public String showIn_Progress(Model model) {
        List<EntryNote> list = journalService.find_in_progress();
        model.addAttribute("list", list);
        return "in_progress";
    }
    @GetMapping("/cancelled")
    public String showCancelled(Model model) {
        List<EntryNote> list = journalService.findCancelled();
        model.addAttribute("list", list);
        return "cancelled";
    }
    @GetMapping("/completed")
    public String showCompleted(Model model) {
        List<EntryNote> list = journalService.findCompleted();
        model.addAttribute("list", list);
        return "completed";
    }
    @GetMapping("/moved")
    public String showMoved( Model model) {
        List<EntryNote> list = journalService.findMoved();
        model.addAttribute("list", list);
        return "moved";
    }

    @GetMapping("/important")
    public String showImportant( Model model) {
        List<EntryNote> list = journalService.findImportant();
        model.addAttribute("list", list);
        return "important";
    }

    @GetMapping("/not_important")
    public String showNotImportant( Model model) {
        List<EntryNote> list = journalService.findNotImportant();
        model.addAttribute("list", list);
        return "not_important";
    }
    
}

