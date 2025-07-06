package com.example.projectdivide.mapper;

import com.example.projectdivide.DTO.SprintDTO;
import com.example.projectdivide.Entity.Sprint;
import com.example.projectdivide.Repository.EmployeeRepository;
import com.example.projectdivide.Repository.SprintRepository;
import com.example.projectdivide.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class SprintDTOMapper {


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SprintRepository sprintRepository;

    SprintDTO toDTO(Sprint sprint){

        SprintDTO sprintDTO = new SprintDTO();

        sprintDTO.setSprintId(sprint.getSprintId());
        sprintDTO.setSprintTitle(sprint.getSprintTitle());
        sprintDTO.setSprintDesc(sprint.getSprintDesc());
        sprintDTO.setStartDate(sprint.getStartDate());
        sprintDTO.setEndDate(sprint.getEndDate());

        return sprintDTO;

    }


    Sprint toEntity(SprintDTO sprintDTO) {
        Sprint sprint = new Sprint();

        Optional<Sprint> sprintOptional = sprintRepository.findBySprintId(sprintDTO.getSprintId());

        if (sprintOptional.isPresent()) {
            sprint.setSprintId(sprintDTO.getSprintId());
            sprint.setSprintTitle(sprintDTO.getSprintTitle());
            sprint.setSprintDesc(sprintDTO.getSprintDesc());
            sprint.setStartDate(sprintDTO.getStartDate());
            sprint.setEndDate(sprintDTO.getEndDate());
            sprint.setEmployee(sprintOptional.get().getEmployee());

            return sprint;
//
//            munji sas iya ti kare uya ti kare maa shaa kaya yarr  jaam d
//                    subhu jo jai ram ji au shyam jo disco night ta kayu ker disandho
//                    subhu jo sita shaym ache ke shela (Shela shela ke jawani im to sexy for ya mei tere haat na aani wale )
//                    laila o mai laila taa kan disco mei
//                    jutha sab malloom hai
//                    yeh kaunsa gana hai bhai?
//                    laila o laila kaise to laila wala suna hai yeh nahi bhai
//
//        aunty implements runnable {
//                void run(){
//                    Thread sasuma = new Thread();
//                    if(sasuma.awake()) System.out.println("Bolo jai mata di");
//                    sasuma.sleep(5000000);
//                    this.party(this);
//
//                    "yarr jaam ghotan tang kayo aa,subuha sham chaayu chaayu khapan asa haane dj
//                }
//
//            }
//            i m craving cheesy fries :( cheeeeeeeeeeeeeee cheeeee alai konse jungle se aaye ho kabi ghaas khate ho brocolli kaabi patagobhi kabi ) beet juice piyo  cravings he mar jayege
//                    healty raho khao piyo aish karooooooo   tum raho dimag ki kami hai     vedant ko dho beetroot juice taki apna mooh and ra khe lamoooooooo  lol
        }
        else{
            throw new RuntimeException("Bebu nahi mila hai usko sprint ");
        }
    }
}
