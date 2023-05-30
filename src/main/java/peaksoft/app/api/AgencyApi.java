package peaksoft.app.api;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.app.entity.Agency;
import peaksoft.app.service.AgencySe;

@Controller
@RequestMapping("/agencies")
@RequiredArgsConstructor
//TODO
public class AgencyApi {

    private final AgencySe agencySe;

    @GetMapping
    public String getAllAgency(Model model) {
        model.addAttribute("agencies", agencySe.getAllAgency());
        return "agency/agenciess";
    }

    @GetMapping("/new")
    public String createAgency(Model model) {
        model.addAttribute("newAgency", new Agency());
        return "agency/newAgency";
    }

    @PostMapping("/save")
    public String saveAgency(@ModelAttribute("newAgency") Agency agency) {
        agencySe.saveAgency(agency);
        return "redirect:/agencies";
    }

    @PostMapping("{id}/delete")
    public String deleteAgency(@PathVariable Long id) {
        //TODO REQUEST PARAM
        agencySe.deleteAgencyById(id);
        return "redirect:/agencies";
    }
    @GetMapping("/profile/{agencyId}")
    public String profile(@PathVariable Long agencyId,Model model){
        model.addAttribute("agency",agencySe.getAgencyById(agencyId));
        return "agency/profile";
    }

    @GetMapping("{id}/edit")
    public String editAgency(@PathVariable Long id, Model model) {
        model.addAttribute("updateAgency", agencySe.getAgencyById(id));
        return "agency/updateAgency";

    }

    @PostMapping("/{id}/update")
    public String updateAgency(@ModelAttribute("updateAgency") Agency agency,
                               @PathVariable("id") Long id) {
        agencySe.update(id, agency);
        return "redirect:/agencies";

        //TODO PUT VS PATCH MAPPING
    }
//
//    @GetMapping("/search")
//    public String search(@RequestParam("search") String search,Model model){
//        model.addAttribute("searching",agencySe.search(search));
//        return "agency/searchPage";
//    }
//
//    @GetMapping("/getAgency/{agencyId}")
//    public String resultSearching(@PathVariable Long agencyId,Model model){
//        model.addAttribute("houseList",agencySe.getAllHouseToAgency(agencyId));
//        return "agency/newPageforSearch";
//
//    }

}
