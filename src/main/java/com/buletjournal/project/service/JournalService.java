package com.buletjournal.project.service;

import com.buletjournal.project.exception.ResourceNotFound;
import com.buletjournal.project.model.EntryNote;
import com.buletjournal.project.model.Progress;
import com.buletjournal.project.model.Type;
import com.buletjournal.project.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {
    @Autowired
    private JournalRepository journalRepository;

    public List<EntryNote> getAllNotes() {
        List<EntryNote> list = journalRepository.findAll();
        return list;
    }

    public EntryNote getNoteById(int id) {
        Optional<EntryNote> note = journalRepository.findById(id);
        if (note != null) {
            return note.get();
        } else {
            throw new ResourceNotFound("Note Not Found");
        }
    }

    public EntryNote createNoteOrUpdate(EntryNote newnote) throws ParseException {
        if (newnote.getId() == null) {
            return journalRepository.save(newnote);
        } else {
            Optional<EntryNote> note = journalRepository.findById(newnote.getId());
            if (note != null) {
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateformat.parse(String.valueOf( newnote.getDate()));

                EntryNote updated = note.get();
                updated.setNote(newnote.getNote());
                updated.setType(newnote.getType());
                updated.setProgress(newnote.getProgress());
                updated.setImportant(newnote.getImportant());
                updated.setDate(date);
                updated = journalRepository.save(updated);
                return updated;
            } else {
                newnote = journalRepository.save(newnote);
                return newnote;
            }
        }
    }

    public void deleteNoteById(int id) {
        journalRepository.deleteById(id);
    }


    public List<EntryNote>  findByKeynote(String keyword){
       return journalRepository.findByKeyword(keyword);
    }

    public List<EntryNote>  findByDate(Date date){
        return journalRepository.findByDate(date);
    }

    public List<EntryNote>  findTasks(){
       String  keyword="Task";
        return journalRepository.findByType(keyword);
    }

    public List<EntryNote>  findEvents(){
        String  keyword="Event";
        return journalRepository.findByType(keyword);
    }
    public List<EntryNote>  findAppointments(){
        String  keyword="Appointment";
        return journalRepository.findByType(keyword);
    }


    public List<EntryNote>  find_in_progress(){
        String  keyword="In_Progress";
        return journalRepository.findByProgress(keyword);
    }
    public List<EntryNote>  findCancelled(){
        String  keyword="Cancelled";
        return journalRepository.findByProgress(keyword);
    }
    public List<EntryNote>  findCompleted(){
        String  keyword="Completed";
        return journalRepository.findByProgress(keyword);
    }
    public List<EntryNote>  findMoved(){
        String  keyword="Moved";
        return journalRepository.findByProgress(keyword);
    }


    public List<EntryNote>  findNotImportant(){
        return journalRepository.findByNotImportant();
    }
    public List<EntryNote>  findImportant(){
        return journalRepository.findByImportant();
    }
}
