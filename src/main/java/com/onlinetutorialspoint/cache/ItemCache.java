package com.onlinetutorialspoint.cache;

import com.onlinetutorialspoint.model.Item;
import com.onlinetutorialspoint.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class ItemCache {
    @Autowired
    ItemRepository itemRepo;

    @Cacheable(value="itemCache", key="#id")
    public Item getItem(int id){
        System.out.println("In getItem cache Component..");
        Item item = null;
        try{
            item = itemRepo.getItem(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return item;
    }
    @CacheEvict(value="itemCache",key = "#id")
    public void deleteItem(int id){
        System.out.println("In deleteItem cache Component..");
        itemRepo.deleteItem(id);
    }

    @CachePut(value="itemCache",key = "#id",condition = "#result != null")
    public void addItem(Item item){
        System.out.println("In addItem cache component..");
        itemRepo.addItem(item);
    }

    @CachePut(value="itemCache",key = "#id",condition = "#result != null")
    public void updateItem(Item item){
        System.out.println("In UpdateItem cache Component..");
        itemRepo.updateItem(item);
    }
}
