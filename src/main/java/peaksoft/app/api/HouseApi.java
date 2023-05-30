package peaksoft.app.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.app.entity.House;
import peaksoft.app.enums.HouseType;
import peaksoft.app.repository.AgencyRe;
import peaksoft.app.service.HouseSe;


@Controller
@RequestMapping("/houses/{agencyId}")
@RequiredArgsConstructor
public class HouseApi {

    private final HouseSe houseSe;
    private final AgencyRe agencyRe;

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
        model.addAttribute("apartment", HouseType.APARTMENT.name());
        model.addAttribute("cottage",HouseType.COTTAGE.name());
        model.addAttribute("castle",HouseType.CASTLE.name());
        model.addAttribute("villa",HouseType.VILLA.name());
        return "house/newHouse";

    }

    @PostMapping("/save")
    public String saveHouse(@ModelAttribute("newHouse") House house,
                            @PathVariable("agencyId") Long agencyId){
        houseSe.saveHouse(agencyId,house);
        return "redirect:/houses/{agencyId}";

    }


    @GetMapping("/{houseId}/deleteHouse")
    public String deleteHouse(@PathVariable Long agencyId,
                              @PathVariable Long houseId ){
        houseSe.deleteHouseById(houseId);
        return "redirect:/houses/{agencyId}";


    }

    @GetMapping("/{houseId}/edit")
    public String editHouse(@PathVariable Long agencyId,
                            @PathVariable Long houseId, Model model){
        model.addAttribute("house",houseSe.getHouseById(houseId));
        model.addAttribute(agencyId);
        return "house/updateHouse";

    }

    @PostMapping("/{houseId}/update")
    public String updateHouse(@PathVariable Long agencyId,@PathVariable Long houseId, Model model,
                              @ModelAttribute ("house") House house){
        houseSe.updateHouse(houseId,house);
        model.addAttribute("apartment",HouseType.APARTMENT.name());
        model.addAttribute("cottage",HouseType.COTTAGE.name());
        model.addAttribute("castle",HouseType.CASTLE.name());
        model.addAttribute("villa",HouseType.VILLA.name());
        model.addAttribute(agencyId);
        return "redirect:/houses/{agencyId}";

    }







}
