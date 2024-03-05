package com.raffaelbrandao.bean;


import java.io.Serializable;
import java.util.List;

import org.primefaces.event.RowEditEvent;

import com.raffaelbrandao.model.Item;
import com.raffaelbrandao.service.ItemService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("dtBasicView")
@ViewScoped
public class ItemView implements Serializable {

	private static final long serialVersionUID = 7661425859807027497L;

	private List<Item> items;

    @Inject
    private ItemService service;

    @PostConstruct
    public void init() {
        
        items = service.getItems();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setService(ItemService service) {
        this.service = service;
    }
    
   
    
    public void onRowEdit(RowEditEvent<Item> event) {
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        service.update(event.getObject());
    }

    public void onRowCancel(RowEditEvent<Item> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getName()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {

        Item item = service.generateRandomItem();  
        items.add(item);
        System.out.println(item);
        service.save(item);

        FacesMessage msg = new FacesMessage("Item Product added", String.valueOf(item.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}