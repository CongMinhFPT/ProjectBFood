import { Chip, IconButton } from "@mui/material";
import React from "react";
import RemoveCircleOutlineIcon from "@mui/icons-material/RemoveCircleOutline";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";

const CartItem = () => {
  return (
    <div className="px-5">
      <div className="flex items-center space-x-5">
        <div>
          <img
            className="w-[5rem] h-[5rem] object-cover"
            src="https://cdn.pixabay.com/photo/2023/10/08/13/03/ai-generated-8302143_1280.jpg"
            alt=""
          />
        </div>
        <div className="flex-grow">
          <div className="space-y-1 lg:space-y-3">
            <p>biryani</p>
            <div className="flex items-center justify-between">
              <div className="flex items-center space-x-1">
                <button>
                  <RemoveCircleOutlineIcon />
                </button>
                <div className="w-5 h-5 text-xs flex items-center justify-center">
                  {5}
                </div>
                <button>
                  <AddCircleOutlineIcon />
                </button>
              </div>
            </div>
          </div>
        </div>
        <div className="ml-auto">
          <p>$555</p>
        </div>
      </div>
      <div className="pt-3 space-x-2">
        {[1, 1, 1].map((item) => (
          <Chip label={"bread"} />
        ))}
      </div>
    </div>
  );
};

export default CartItem;
