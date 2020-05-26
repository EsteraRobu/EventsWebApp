package com.main.erobu.services;

import com.main.erobu.data.entry.Editor;
import com.main.erobu.data.repository.EditorRepository;
import com.main.erobu.data.repository.RolesRepository;
import com.main.erobu.dto.EditorDTO;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.util.EntityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EditorService {

    @Autowired
    private EditorRepository editorRepository;
    final String EDITOR_ROLE="EDITOR";
    @Autowired
    private RolesRepository rolesRepository;

    public void registerEditor(EditorDTO editorDTO) {
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        Editor editor = EntityUtils.editorDTOToEditor(editorDTO);
        editor.setPassword(encoder.encode(editorDTO.getPassword()));
        editor.setRole(rolesRepository.findRolesByRole(EDITOR_ROLE).orElse(null));

        editorRepository.save(editor);
    }

    public EditorDTO findByUsername(String username) throws ObjectTransferException {
        Editor editor = editorRepository.findByUsername(username);
        if(editor == null){
            throw new ObjectTransferException("Data not found");
        }
        return EntityUtils.editorToEditorDTO(editor);
    }

    public void save(EditorDTO editorDTO) {
        Editor editor = EntityUtils.editorDTOToEditor(editorDTO);
        editorRepository.save(editor);
    }

    public List<EditorDTO> findPendingEditors(){
        List<EditorDTO> editorDTOList = new ArrayList<>();
        List<Editor> editorList = editorRepository.findByActive(0);
        for(Editor editor: editorList){
            editorDTOList.add(EntityUtils.editorToEditorDTO(editor));
        }
        return editorDTOList;
    }

    public EditorDTO findById(Integer editorId) throws ObjectTransferException {
        Editor editor = editorRepository.findById(editorId).orElse(null);
        if(editor == null){
            throw new ObjectTransferException("Data not found");
        }
        return EntityUtils.editorToEditorDTO(editor);
    }
}
