package com.main.erobu.services;

import com.main.erobu.data.entry.Client;
import com.main.erobu.data.entry.EventCategory;
import com.main.erobu.data.repository.ClientRepository;
import com.main.erobu.data.repository.EventCategoryRepository;
import com.main.erobu.data.repository.RolesRepository;
import com.main.erobu.dto.ClientDTO;
import com.main.erobu.dto.EventCategoryDTO;
import com.main.erobu.exceptions.ObjectTransferException;
import com.main.erobu.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ClientService {
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private EventCategoryRepository eventCategoryRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;
    final String CLIENT_ROLE="CLIENT";

    public void registerClient(ClientDTO clientDTO) {
        clientDTO.setId(0);
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // Set<EventCategory> eventCategoriesList = new HashSet<>();
        Set<EventCategoryDTO> eventCategoriesDtos = new HashSet<>();

        clientDTO.setPassword(encoder.encode(clientDTO.getPassword()));
        Client client = EntityUtils.clientDTOToClient(clientDTO);
        List<String> selectedFavCategories = new ArrayList<>();
        Set<EventCategory> favCategoriestoSet= new HashSet<>();

        if (Objects.nonNull(clientDTO.getMultiCheckboxSelectedValues())) {
            selectedFavCategories = clientDTO.getMultiCheckboxSelectedValues();
            for (String favCategory : selectedFavCategories) {
                EventCategory eventCategory=eventCategoryRepository.findByCategory(favCategory);
                favCategoriestoSet.add(eventCategory);
            }
        }
        client.setEventcategories(favCategoriestoSet);
        client.setRole(rolesRepository.findRolesByRole(CLIENT_ROLE).orElse(null));
        client=clientRepository.save(client);
        clientDTO = EntityUtils.clientToClientDTO(client);
        shoppingCartService.create(clientDTO);
    }

    public ClientDTO findByUsername(String username) throws ObjectTransferException {
        Client client = clientRepository.findByUsername(username);
        if (client == null) {
            throw new ObjectTransferException("Data not found");
        }
        return EntityUtils.clientToClientDTO(client);
    }



    public void delete(ClientDTO clientDTO) {
        Client client = EntityUtils.clientDTOToClient(clientDTO);
        clientRepository.delete(client);
    }
}
