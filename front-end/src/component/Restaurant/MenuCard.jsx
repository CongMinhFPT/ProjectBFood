import React from "react";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import {
  Accordion,
  AccordionDetails,
  AccordionSummary,
  Button,
  Checkbox,
  FormControlLabel,
  FormGroup,
} from "@mui/material";

const demo = [
  {
    category: "Nuts & seeds",
    ingerdients: ["Cashews"],
  },
  {
    category: "Protein",
    ingerdients: ["Protein", "Bacon strips"],
  },
];
const MenuCard = () => {
  return (
    <Accordion>
      <AccordionSummary
        expandIcon={<ExpandMoreIcon />}
        aria-controls="panel1-content"
        id="panel1-header"
      >
        <div className="lg:flex items-center justify-between">
          <div className="lg:flex items-center lg:gap-5">
            <img
              className="w-[7rem] h-[7rem] object-cover"
              src="https://cdn.pixabay.com/photo/2023/10/08/13/03/ai-generated-8302143_1280.jpg"
              alt=""
            />
            <div className="space-y-1 lg:space-y-5 lg:max-w-2xl">
              <p className="font-semibold text-xl">Burger</p>
              <p>$499</p>
              <p className="text-gray-400">nice food</p>
            </div>
          </div>
        </div>
      </AccordionSummary>
      <AccordionDetails>
        <form>
          <div className="flex gap-5 flex-wrap">
            {demo.map((item, index) => (
              <div key={index}>
                <p>{item.category}</p>
                <FormGroup>
                  {item.ingerdients.map((ingredient, idx) => (
                    <FormControlLabel
                      key={idx}
                      control={<Checkbox />}
                      label={ingredient}
                    />
                  ))}
                </FormGroup>
              </div>
            ))}
          </div>
          <div className="pt-8">
            <Button variant="contained" disabled={false} type="submit">
              {true ? "Add to Cart" : "Out Of Stock"}
            </Button>
          </div>
        </form>
      </AccordionDetails>
    </Accordion>
  );
};

export default MenuCard;
