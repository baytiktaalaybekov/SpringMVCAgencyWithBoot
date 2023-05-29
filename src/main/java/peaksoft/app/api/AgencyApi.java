package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Agency;
import peaksoft.service.AgencySe;

@Controller
@RequestMapping("/agencies")
@RequiredArgsConstructor
public class AgencyApi {

    private final AgencySe agencySe;

    @GetMapping
    public String getAllAgency(Model model,@RequestParam(value = "word",required = false)String word) {
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

    @DeleteMapping("{id}/delete")
    public String deleteAgency(@PathVariable Long id) {
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

    @PutMapping("/{id}/update")
    public String updateAgency(@ModelAttribute("updateAgency") Agency agency,
                               @PathVariable("id") Long id) {
        agencySe.updateAgency(id, agency);
        return "redirect:/agencies";
    }

    @GetMapping("/search")
    public String search(@RequestParam("search") String search,Model model){
        model.addAttribute("searching",agencySe.search(search));
        return "agency/searchPage";
    }

    @GetMapping("/getAgency/{agencyId}")
    public String resultSearching(@PathVariable Long agencyId,Model model){
        model.addAttribute("houseList",agencySe.getAllHouseToAgency(agencyId));
        return "agency/newPageforSearch";

    }

}
