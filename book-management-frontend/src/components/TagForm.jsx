import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function TagForm() {
    const [name, setName] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!name.trim()) {
            alert("Tag name cannot be empty");
            return;
        }

        try {
            await axios.post(
                "http://localhost:8080/api/tags",
                { name },
                { headers: { "Content-Type": "application/json" } }
            );
            navigate("/tags");
        } catch (err) {
            console.error(err);
            alert("Error creating tag");
        }
    };

    return (
        <div className="p-4">
            <h2 className="text-2xl font-bold mb-4">Add Tag</h2>
            <form onSubmit={handleSubmit} className="space-y-2 max-w-md">
                <input
                    type="text"
                    placeholder="Tag Name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    className="border p-2 w-full"
                    required
                />
                <button
                    type="submit"
                    className="bg-blue-500 text-white px-4 py-2 rounded"
                >
                    Create Tag
                </button>
            </form>
        </div>
    );
}
