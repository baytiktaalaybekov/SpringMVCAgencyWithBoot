package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.House;
import peaksoft.enums.HouseType;
import peaksoft.service.HouseSe;

@Controller
@RequestMapping("/houses/{agencyId}")
@RequiredArgsConstructor
public class HouseApi {

    private final HouseSe houseSe;

    @GetMapping()
    public String getAllHouse(@PathVariable Long agencyId, Model model){
        model.addAttribute("house",houseSe.getAllHouse(agencyId));
        model.addAttribute(agencyId);
        return "house/houses";

    }

    @GetMapping("/new")
    public String createHouse(Model model, @PathVariable Long agencyId){
        model.addAttribute("newHouse", new House());
        model.addAttribute(agencyId);
        model.addAttribute("apartment",HouseType.APARTMENT.name());
        model.addAttribute("cottage",HouseType.COTTAGE.name());
        model.addAttribute("castle",HouseType.CASTLE.name());
        model.addAttribute("villa",HouseType.VILLA.name());
        return "house/newHouse";

    }

    @PostMapping("/save")
    public String saveHouse(@ModelAttribute("newHouse") House house,
                            @PathVariable("agencyId") Long id){
        houseSe.saveHouse(id,house);
        return "redirect:/houses/" + id;

    }


    @GetMapping("/{houseId}/deleteHouse")
    public String deleteHouse(@PathVariable Long agencyId,
                              @PathVariable Long houseId ){
        houseSe.deleteHouse(houseId);
        return "redirect:/houses/{agencyId}";


    }

    @GetMapping("/{houseId}/edit")
    public String editHouse(@PathVariable Long agencyId,
                            @PathVariable Long houseId, Model model){
        model.addAttribute("house",houseSe.getHouseById(houseId));
        return "house/updateHouse";

    }

    @PutMapping("/{houseId}/update")
    public String updateHouse(@PathVariable Long agencyId,@PathVariable Long houseId, Model model,
                              @ModelAttribute ("house") House house){
        houseSe.updateHouse(houseId,house);
        model.addAttribute("apartment",HouseType.APARTMENT.name());
        model.addAttribute("cottage",HouseType.COTTAGE.name());
        model.addAttribute("castle",HouseType.CASTLE.name());
        model.addAttribute("villa",HouseType.VILLA.name());
        return "redirect:/houses/{agencyId}";

    }







}
